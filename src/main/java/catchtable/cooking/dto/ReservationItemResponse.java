package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationItemResponse {

    private Long id;

    private String status;

    private LocalTime time;

    public static ReservationItemResponse of(Reservation reservation) {
        return ReservationItemResponse.builder()
                .id(reservation.getId())
                .status(reservation.getStatus())
                .time(reservation.getReservationTime())
                .build();
    }

}
