package catchtable.cooking.service;

import catchtable.cooking.dto.*;
import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import catchtable.cooking.persist.domain.*;
import catchtable.cooking.persist.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final WaitingRepository waitingRepository;
    private final MenuRepository menuRepository;

    public RestaurantItemResponse readRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                () -> new CustomException(Code.RESTAURANT_ID_NOT_EXIST)
        );

        List<MenuItemResponse> menus = menuRepository.findAllByRestaurant(restaurant).stream()
                .map(MenuItemResponse::of)
                .toList();
        List<ReviewItemResponse> reviews = reviewRepository.findAllByRestaurant(restaurant).stream()
                .map(ReviewItemResponse::of)
                .toList();
        List<WaitingItemResponse> waitings = waitingRepository.findAllByRestaurant(restaurant).stream()
                .map(WaitingItemResponse::of)
                .toList();
        List<ReservationItemResponse> reservations = reservationRepository.findAllByRestaurant(restaurant).stream()
                .map(ReservationItemResponse::of)
                .toList();

        return RestaurantItemResponse.builder()
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .menus(menus)
                .reviews(reviews)
                .waitings(waitings)
                .reservations(reservations)
                .build();
    }

    public List<RestaurantItemResponse> readRestaurants(String keyword) {
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
