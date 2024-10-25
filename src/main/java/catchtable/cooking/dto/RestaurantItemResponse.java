package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String menu;

    List<Review> reviews = new ArrayList<>();

}
