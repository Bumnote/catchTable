package catchtable.cooking.controller;

import catchtable.cooking.dto.RestaurantCreateParam;
import catchtable.cooking.dto.RestaurantCreateRequest;
import catchtable.cooking.dto.HttpResponse;
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
    public ResponseEntity<HttpResponse> readEntireRestaurant(@RequestParam(value = "name", required = false) String name) {
        log.info("name: {}", name);
        List<Restaurant> restaurantList;

        if (name == null) {
            restaurantList = restaurantService.readEntireRestaurant();
        } else {
            restaurantList = restaurantService.readParamRestaurant(name);
        }

        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK, HttpStatus.OK.toString(), restaurantList));
    }

    @PostMapping("/restaurants")
    public ResponseEntity<HttpResponse> createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        // RequestBody로 받은 정보들을 model에 저장
        restaurantService.createRestaurant(restaurantCreateRequest);
        return ResponseEntity.ok(HttpResponse.res(HttpStatus.CREATED, HttpStatus.CREATED.toString(), restaurantCreateRequest));
    }



    @GetMapping("/restaurants/{id}")
    public ResponseEntity<HttpResponse> readRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.readRestaurant(id);
        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK, HttpStatus.OK.toString(), restaurant));
    }

    @PutMapping("/restaurants/{id}")
    public ResponseEntity<HttpResponse> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        RestaurantCreateParam restaurantCreateParam = RestaurantCreateParam.builder()
                .name(restaurantCreateRequest.getName())
                .phoneNumber(restaurantCreateRequest.getPhoneNumber())
                .address(restaurantCreateRequest.getAddress())
                .menu(restaurantCreateRequest.getMenu())
                .build();

        restaurantService.updateRestaurant(id, restaurantCreateParam);
        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK, HttpStatus.OK.toString()));
    }


    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<HttpResponse> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK, HttpStatus.OK.toString()));
    }

}
