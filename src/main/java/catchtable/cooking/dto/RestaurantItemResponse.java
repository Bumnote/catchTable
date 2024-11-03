package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.Menu;
import catchtable.cooking.persist.domain.Review;
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

    private String phoneNumber;

    private String address;

    private List<Menu> menus = new ArrayList<>();

    private List<Review> reviews = new ArrayList<>();


}
