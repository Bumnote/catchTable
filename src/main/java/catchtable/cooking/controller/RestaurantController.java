package catchtable.cooking.controller;

import catchtable.cooking.dto.RestaurantCreateRequest;
import catchtable.cooking.dto.HttpResponse;
import catchtable.cooking.dto.RestaurantItemResponse;
import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public ResponseEntity<HttpResponse> readEntireRestaurant(@RequestParam(value = "keyword", required = false) String keyword) {

        List<Restaurant> restaurantList = restaurantService.readEntireRestaurant(keyword);

        List<RestaurantItemResponse> restaurantItemResponse = restaurantList.stream()
                .map(restaurant -> new RestaurantItemResponse(
                        restaurant.getName(),
                        restaurant.getPhoneNumber(),
                        restaurant.getAddress(),
                        restaurant.getMenu(),
                        restaurant.getReviews()
                )).collect(Collectors.toList());

        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK.value(), HttpStatus.OK.toString(), restaurantItemResponse));
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<HttpResponse> readRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.readRestaurant(id);

        RestaurantItemResponse restaurantItemResponse = RestaurantItemResponse.builder()
                .name(restaurant.getName())
                .phoneNumber(restaurant.getPhoneNumber())
                .address(restaurant.getAddress())
                .menu(restaurant.getMenu())
                .reviews(restaurant.getReviews())
                .build();

        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK.value(), HttpStatus.OK.toString(), restaurantItemResponse));
    }

    @PostMapping("/restaurants")
    public ResponseEntity<HttpResponse> createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        restaurantService.createRestaurant(restaurantCreateRequest);
        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK.value(), HttpStatus.OK.toString(), "success"));
    }


}
