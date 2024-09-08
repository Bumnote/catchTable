package catchtable.cooking.service;

import catchtable.cooking.domain.Restaurant;
import catchtable.cooking.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    // 생성자 주입
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // 식당 정보 저장
    public void createRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    // 특정 식당 정보 조회
    public Restaurant readRestaurant(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

}
