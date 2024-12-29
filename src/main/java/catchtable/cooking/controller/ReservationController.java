package catchtable.cooking.controller;

import catchtable.cooking.dto.CommonResponse;
import catchtable.cooking.dto.ReservationCreateRequest;
import catchtable.cooking.exception.Code;
import catchtable.cooking.lock.RedissonLockReservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/customers/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final RedissonLockReservation redissonLockReservation;

    @PostMapping
    public CommonResponse<?> createReservation(@RequestBody ReservationCreateRequest reservationCreateRequest) {

        redissonLockReservation.createReservation(reservationCreateRequest);

        return CommonResponse.of(Code.OK);
    }

}
