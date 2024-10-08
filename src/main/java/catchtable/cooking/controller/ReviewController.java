package catchtable.cooking.controller;

import catchtable.cooking.dto.HttpResult;
import catchtable.cooking.dto.ReviewCreateRequest;
import catchtable.cooking.persist.domain.Review;
import catchtable.cooking.service.RestaurantService;
import catchtable.cooking.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final RestaurantService restaurantService;


    @GetMapping("/restaurants/{id}/reviews")
    public ResponseEntity<HttpResult> readReviews(@PathVariable Long id) {
        List<Review> reviewEntities = reviewService.readReviews(id);
        if (reviewEntities == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(HttpResult.res(HttpStatus.OK, HttpStatus.OK.toString(), reviewEntities));
    }

    @PostMapping("/restaurants/{id}/reviews")
    public ResponseEntity<HttpResult> postReview(@PathVariable Long id, @RequestBody ReviewCreateRequest reviewCreateRequest) {
        reviewService.createReview(id, reviewCreateRequest);
        return ResponseEntity.ok(HttpResult.res(HttpStatus.CREATED, HttpStatus.CREATED.toString(), reviewCreateRequest));
    }


}
