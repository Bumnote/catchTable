package catchtable.cooking.controller;

import catchtable.cooking.dto.RestaurantCreateRequest;
import catchtable.cooking.dto.HttpResult;
import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public ResponseEntity<HttpResult> readEntireRestaurant(@RequestParam(value = "name", required = false) String name) {
        log.info("name: {}", name);
        List<Restaurant> restaurantList;

        if (name == null) {
            restaurantList = restaurantService.readEntireRestaurant();
        } else {
            restaurantList = restaurantService.readParamRestaurant(name);
        }

        return ResponseEntity.ok(HttpResult.res(HttpStatus.OK, HttpStatus.OK.toString(), restaurantList));
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<HttpResult> readRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.readRestaurant(id);
        return ResponseEntity.ok(HttpResult.res(HttpStatus.OK, HttpStatus.OK.toString(), restaurant));
    }


    @PostMapping("/restaurants")
    public ResponseEntity<HttpResult> createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        // RequestBody로 받은 정보들을 model에 저장
        restaurantService.createRestaurant(restaurantCreateRequest);
        return ResponseEntity.ok(HttpResult.res(HttpStatus.CREATED, HttpStatus.CREATED.toString(), restaurantCreateRequest));
    }


}
