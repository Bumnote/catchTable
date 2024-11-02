package catchtable.cooking.controller;

import catchtable.cooking.dto.CommonResponse;
import catchtable.cooking.dto.RestaurantCreateParam;
import catchtable.cooking.dto.RestaurantCreateRequest;
import catchtable.cooking.dto.RestaurantItemResponse;
import catchtable.cooking.exception.Code;
import catchtable.cooking.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public CommonResponse<?> readRestaurants(@RequestParam(value = "keyword", required = false) String keyword) {

        List<RestaurantItemResponse> restaurantItemResponses = restaurantService.readRestaurants(keyword);

        return CommonResponse.of(restaurantItemResponses);
    }

    @PostMapping("/restaurants")
    public CommonResponse<?> createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        restaurantService.createRestaurant(restaurantCreateRequest);
        return CommonResponse.of(Code.OK);
    }


    @GetMapping("/restaurants/{id}")
    public CommonResponse<?> readRestaurant(@PathVariable Long id) {
        RestaurantItemResponse restaurantItemResponse = restaurantService.readRestaurant(id);
        return CommonResponse.of(restaurantItemResponse);
    }


    @PutMapping("/restaurants/{id}")
    public CommonResponse<?> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantCreateRequest restaurantCreateRequest) {

        restaurantService.updateRestaurant(id, new RestaurantCreateParam(restaurantCreateRequest));
        return CommonResponse.of(Code.OK);
    }

    @DeleteMapping("/restaurants/{id}")
    public CommonResponse<?> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return CommonResponse.of(Code.OK);
    }

}
