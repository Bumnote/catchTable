package catchtable.cooking.service;

import catchtable.cooking.dto.RestaurantCreateRequest;
import catchtable.cooking.exception.ErrorCode;
import catchtable.cooking.exception.IdNotExistException;
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

    public List<Restaurant> readParamRestaurant(String name) {
        // 이름이 같은 음식점들도 있으니, 이름 파라미터에 대한 모든 식당을 조회하자.
        return restaurantRepository.findAllByName(name);
    }


    public List<Restaurant> readEntireRestaurant() {
        return restaurantRepository.findAll();
    }

    public void createRestaurant(RestaurantCreateRequest restaurant) {
        Restaurant restaurantEntity = new Restaurant(restaurant);
        restaurantRepository.save(restaurantEntity);
    }

    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new IdNotExistException("해당 ID의 식당이 존재하지 않습니다.", ErrorCode.RESTAURANT_ID_NOT_EXIST);
        }
        restaurantRepository.deleteById(id);
    }
}
