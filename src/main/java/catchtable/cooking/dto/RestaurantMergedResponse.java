package catchtable.cooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMergedResponse {

    private RestaurantItemDetailResponse restaurant;

    private List<ReviewItemResponse> reviews;
}
