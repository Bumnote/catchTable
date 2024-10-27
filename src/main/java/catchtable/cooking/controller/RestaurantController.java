package catchtable.cooking.controller;

import catchtable.cooking.dto.*;
import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public CommonResponse<?> readEntireRestaurant(@RequestParam(value = "keyword", required = false) String keyword) {

        List<Restaurant> restaurantList = restaurantService.readEntireRestaurant(keyword);
        List<RestaurantItemResponse> restaurantItemResponse = restaurantList.stream()
                .map(restaurant -> new RestaurantItemResponse(
                        restaurant.getName(),
                        restaurant.getPhoneNumber(),
                        restaurant.getAddress(),
                        restaurant.getMenu(),
                        restaurant.getReviews()
                )).collect(Collectors.toList());

        return CommonResponse.of(restaurantItemResponse);
    }

    @PostMapping("/restaurants")
    public CommonResponse<?> createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        restaurantService.createRestaurant(restaurantCreateRequest);
        return CommonResponse.of("success");
    }


    @GetMapping("/restaurants/{id}")
    public CommonResponse<?> readRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.readRestaurant(id);
        return CommonResponse.of(restaurant);
    }

    @PutMapping("/restaurants/{id}")
    public CommonResponse<?> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantCreateRequest restaurantCreateRequest) {

        restaurantService.updateRestaurant(id, new RestaurantCreateParam(restaurantCreateRequest));
        return CommonResponse.of("success");
    }

    @DeleteMapping("/restaurants/{id}")
    public CommonResponse<?> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return CommonResponse.of("success");
    }

}
