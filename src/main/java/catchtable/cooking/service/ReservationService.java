package catchtable.cooking.service;

import catchtable.cooking.dto.ReservationCreateRequest;
import catchtable.cooking.persist.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;


    public void createReservation(ReservationCreateRequest reservationCreateRequest) {

        /*

        예약 서비스 기능 구현 내용

         */

    }

}
