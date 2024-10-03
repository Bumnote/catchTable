package catchtable.cooking.service;

import catchtable.cooking.dto.RestaurantCreateRequest;
import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.persist.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant readRestaurant(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public List<Restaurant> readEntireRestaurant(String keyword) {
        return restaurantRepository.getRestaurants(keyword);
    }

    public void createRestaurant(RestaurantCreateRequest restaurant) {
        Restaurant restaurantEntity = new Restaurant(restaurant);
        restaurantRepository.save(restaurantEntity);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
