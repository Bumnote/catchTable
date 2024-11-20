package catchtable.cooking.persist.repository;

import catchtable.cooking.dto.RestaurantItemResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepositoryCustom {

    List<RestaurantItemResponse> getRestaurants(String keyword);

}
