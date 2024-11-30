package catchtable.cooking.controller;

import catchtable.cooking.dto.CommonResponse;
import catchtable.cooking.dto.RestaurantCreateParam;
import catchtable.cooking.dto.RestaurantCreateRequest;
import catchtable.cooking.exception.Code;
import catchtable.cooking.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/owners/")
@RequiredArgsConstructor
public class OwnerController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurants")
    public CommonResponse<?> createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        restaurantService.createRestaurant(new RestaurantCreateParam().of(restaurantCreateRequest));
        return CommonResponse.of(Code.OK);
    }

    @DeleteMapping("/{id}/restaurants")
    public CommonResponse<?> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return CommonResponse.of(Code.OK);
    }

    @PutMapping("/{id}/restaurants")
    public CommonResponse<?> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantCreateRequest restaurantCreateRequest) {

        restaurantService.updateRestaurant(id, new RestaurantCreateParam().of(restaurantCreateRequest));
        return CommonResponse.of(Code.OK);
    }

}
