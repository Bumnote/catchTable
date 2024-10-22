package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepositoryCustom {

    List<Restaurant> getRestaurants(String keyword);

}
