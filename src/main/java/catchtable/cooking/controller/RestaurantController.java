package catchtable.cooking.controller;

import catchtable.cooking.model.Restaurant;
import catchtable.cooking.model.Result;
import catchtable.cooking.persist.domain.RestaurantEntity;
import catchtable.cooking.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 생성자 의존성 주입
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    // 전체 식당 리스트 조회
    @GetMapping("/restaurant/entire")
    public ResponseEntity<Result> readEntireRestaurant() {
        List<RestaurantEntity> restaurantEntityList = restaurantService.readEntireRestaurant();
        return ResponseEntity.ok(Result.res(HttpStatus.OK, HttpStatus.OK.toString(), restaurantEntityList));
    }

    // 특정 식당 정보 조회 (상세 페이지)
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Result> readRestaurant(@PathVariable Long id) {
        RestaurantEntity restaurantEntity = restaurantService.readRestaurant(id);
        return ResponseEntity.ok(Result.res(HttpStatus.OK, HttpStatus.OK.toString(), restaurantEntity));
    }


    // 식당 정보 저장
    @PostMapping("/restaurant/post")
    public ResponseEntity<Result> createRestaurant(@RequestBody Restaurant restaurant) {
        // RequestBody로 받은 정보들을 model에 저장
        restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(Result.res(HttpStatus.CREATED, HttpStatus.CREATED.toString(), restaurant));
    }


}
