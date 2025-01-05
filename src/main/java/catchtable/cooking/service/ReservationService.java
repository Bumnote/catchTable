package catchtable.cooking.service;

import catchtable.cooking.dto.MemberJwtDTO;
import catchtable.cooking.dto.ReservationCreateParam;
import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import catchtable.cooking.lock.LockUtil;
import catchtable.cooking.lock.RedissonLockProvider;
import catchtable.cooking.persist.domain.Member;
import catchtable.cooking.persist.domain.Reservation;
import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.persist.repository.MemberRepository;
import catchtable.cooking.persist.repository.ReservationRepository;
import catchtable.cooking.persist.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RedissonLockProvider redissonLockProvider;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    private final static int RESERVATION_MAX_COUNT = 8;
    private final static int WAIT_TIME = 20;
    private final static int LEASE_TIME = 1;
    private final static TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    public void createReservation(ReservationCreateParam param, MemberJwtDTO memberJwtDTO) {

        LockUtil lockUtil = new LockUtil(WAIT_TIME, LEASE_TIME, TIME_UNIT);
        boolean locked = redissonLockProvider.getLock(param, lockUtil,
                () -> {
                    saveReservation(param, memberJwtDTO);
                }
        );

        if (!locked) {
            throw new IllegalStateException("락을 획득하지 못했습니다.");
        }

    }

    public void saveReservation(ReservationCreateParam param,
                                MemberJwtDTO memberJwtDTO) {

        LocalDate date = param.getReservationInfo().getDate();
        LocalTime time = param.getReservationInfo().getTime();

        int reservationCount = reservationRepository.countByReservationDateAndReservationTime(date, time);
        if (reservationCount >= RESERVATION_MAX_COUNT) {
            throw new CustomException(Code.RESERVATION_OVERBOOKING);
        }
        Member member = memberRepository.findById(memberJwtDTO.getId()).orElseThrow(
                () -> new CustomException(Code.NOT_EXIST_MEMBER)
        );

        Restaurant restaurant = restaurantRepository.findById(param.getRestaurantId()).orElseThrow(
                () -> new CustomException(Code.RESTAURANT_ID_NOT_EXIST)
        );

        Reservation reservation = new Reservation(param, member, restaurant);
        reservationRepository.save(reservation);
    }

}
