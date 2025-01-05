package catchtable.cooking.controller;

import catchtable.cooking.dto.CommonResponse;
import catchtable.cooking.dto.MemberJwtDTO;
import catchtable.cooking.dto.ReservationCreateParam;
import catchtable.cooking.dto.ReservationCreateRequest;
import catchtable.cooking.exception.Code;
import catchtable.cooking.service.ReservationService;
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

    private final ReservationService reservationService;

    @PostMapping
    public CommonResponse<?> createReservation(@RequestBody ReservationCreateRequest reservationCreateRequest,
                                               MemberJwtDTO memberJwtDTO) {

        reservationService.createReservation(new ReservationCreateParam().of(reservationCreateRequest), memberJwtDTO);

        return CommonResponse.of(Code.OK);
    }

}
