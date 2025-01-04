package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantQueryResponse {

    private Restaurant restaurant;

    private Menu menu;

    private Review review;

    private Waiting waiting;

    private Reservation reservation;
}
