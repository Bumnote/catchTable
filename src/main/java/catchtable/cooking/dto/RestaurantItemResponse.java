package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.Menu;
import catchtable.cooking.persist.domain.Reservation;
import catchtable.cooking.persist.domain.Review;
import catchtable.cooking.persist.domain.Waiting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantItemResponse {

    private String name;

    private String address;

    private String phoneNumber;

    private List<Menu> menus = new ArrayList<>();

    private List<Review> reviews = new ArrayList<>();

    private List<Waiting> waitings = new ArrayList<>();

    private List<Reservation> reservations = new ArrayList<>();

}
