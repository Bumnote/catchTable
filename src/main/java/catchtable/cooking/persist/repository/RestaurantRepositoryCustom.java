package catchtable.cooking.persist.repository;

import catchtable.cooking.dto.RestaurantItemResponse;
import catchtable.cooking.dto.RestaurantQueryResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepositoryCustom {

    List<RestaurantQueryResponse> getAllRestaurants(String keyword);

    List<RestaurantItemResponse> getRestaurants(String keyword);

}
