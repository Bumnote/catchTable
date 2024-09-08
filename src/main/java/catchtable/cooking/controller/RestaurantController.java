package catchtable.cooking.controller;

import catchtable.cooking.domain.Restaurant;
import catchtable.cooking.dto.RestaurantDto;
import catchtable.cooking.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 생성자 의존성 주입
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping("/restaurant/entire")
    public void readRestaurant() {

    }

    // 특정 식당 정보 조회
    @GetMapping("/restaurant/{id}")
    public Restaurant readRestaurant(@PathVariable Long id) {
        return restaurantService.readRestaurant(id);
    }

    // 식당 정보 저장
    @PostMapping("/restaurant/create")
    public void createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDto.getName())
                .address(restaurantDto.getAddress())
                .menu(restaurantDto.getMenu())
                .phoneNumber(restaurantDto.getPhoneNumber())
                .build();
        log.info("Restaurant created: {}", restaurant);
        restaurantService.createRestaurant(restaurant);
    }


}
