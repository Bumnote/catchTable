package catchtable.cooking.service;

import catchtable.cooking.model.Restaurant;
import catchtable.cooking.persist.domain.RestaurantEntity;
import catchtable.cooking.persist.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    // 생성자 주입
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    // 특정 식당 정보 조회
    public RestaurantEntity readRestaurant(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    // 전체 식당 정보 조회
    public List<RestaurantEntity> readEntireRestaurant() {
        return restaurantRepository.findAll();
    }

    // 식당 정보 저장
    public void createRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurant);
        restaurantRepository.save(restaurantEntity);
    }
}
