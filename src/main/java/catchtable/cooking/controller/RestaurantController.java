package catchtable.cooking.controller;

import catchtable.cooking.dto.CommonResponse;
import catchtable.cooking.dto.MenuItemResponse;
import catchtable.cooking.dto.RestaurantItemDetailResponse;
import catchtable.cooking.dto.RestaurantItemResponse;
import catchtable.cooking.service.RestaurantService;
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

    @GetMapping("")
    public CommonResponse<?> readRestaurants(@RequestParam(value = "keyword", required = false) String keyword) {

        List<RestaurantItemResponse> restaurantItemResponses = restaurantService.readRestaurants(keyword);

        return CommonResponse.of(restaurantItemResponses);
    }

    @GetMapping("/{id}")
    public CommonResponse<?> readRestaurant(@PathVariable Long id) {
        RestaurantItemDetailResponse restaurantItemDetailResponse = restaurantService.readRestaurant(id);
        return CommonResponse.of(restaurantItemDetailResponse);
    }


    @GetMapping("/{id}/menus")
    public CommonResponse<?> readRestaurantMenus(@PathVariable Long id) {
        List<MenuItemResponse> menus = restaurantService.readRestaurantMenus(id);
        return CommonResponse.of(menus);
    }

}
