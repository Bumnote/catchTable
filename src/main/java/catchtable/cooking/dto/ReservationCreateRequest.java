package catchtable.cooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateRequest {

    private Long restaurantId;

    private Long customerId;

    private ReservationInfo reservationInfo;

}
