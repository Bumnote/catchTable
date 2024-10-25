package catchtable.cooking.service;

import catchtable.cooking.dto.RestaurantCreateParam;
import catchtable.cooking.dto.RestaurantCreateRequest;
import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
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

    public void createRestaurant(RestaurantCreateRequest restaurantCreateRequest) {
        Restaurant restaurant = new Restaurant(restaurantCreateRequest);
        restaurantRepository.save(restaurant);
    }

    public void updateRestaurant(Long id, RestaurantCreateParam restaurantCreateParam) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new CustomException(Code.RESTAURANT_ID_NOT_EXIST));

        Restaurant restaurantParam = Restaurant.builder()
                .id(restaurant.getId())
                .name(restaurantCreateParam.getName())
                .phoneNumber(restaurantCreateParam.getPhoneNumber())
                .address(restaurantCreateParam.getAddress())
                .menu(restaurantCreateParam.getMenu())
                .reviews(restaurant.getReviews())
                .build();

        restaurantRepository.save(restaurantParam);
    }

    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new CustomException(Code.RESTAURANT_ID_NOT_EXIST);
        }
        restaurantRepository.deleteById(id);
    }
}
