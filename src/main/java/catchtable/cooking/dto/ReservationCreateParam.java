package catchtable.cooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateParam {

    private Long restaurantId;

    private Long customerId;

    private ReservationInfo reservationInfo;

    public ReservationCreateParam of(ReservationCreateRequest request) {
        return ReservationCreateParam.builder()
                .restaurantId(request.getRestaurantId())
                .customerId(request.getCustomerId())
                .reservationInfo(request.getReservationInfo())
                .build();
    }
}
