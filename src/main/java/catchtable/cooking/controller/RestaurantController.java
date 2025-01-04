package catchtable.cooking.controller;

import catchtable.cooking.dto.*;
import catchtable.cooking.service.RestaurantService;
import catchtable.cooking.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customers/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    @GetMapping
    public CommonResponse<?> readRestaurants(@RequestParam(value = "keyword", required = false) String keyword) {

        List<RestaurantItemResponse> restaurantItemResponses = restaurantService.readRestaurants(keyword);

        return CommonResponse.of(restaurantItemResponses);
    }

    @GetMapping("/{id}")
    public CommonResponse<?> readRestaurant(@PathVariable Long id) {

        RestaurantItemDetailResponse restaurantItemDetailResponse = restaurantService.readRestaurant(id);
        List<ReviewItemResponse> reviewItemResponses = reviewService.getReviews(id);

        RestaurantMergedResponse restaurantMergedResponse = RestaurantMergedResponse.builder()
                .restaurant(restaurantItemDetailResponse)
                .reviews(reviewItemResponses).build();

        return CommonResponse.of(restaurantMergedResponse);
    }


    @GetMapping("/{id}/menus")
    public CommonResponse<?> readRestaurantMenus(@PathVariable Long id) {
        List<MenuItemResponse> menus = restaurantService.readRestaurantMenus(id);
        return CommonResponse.of(menus);
    }

}
