package catchtable.cooking.controller;

import catchtable.cooking.model.Result;
import catchtable.cooking.model.Review;
import catchtable.cooking.persist.domain.ReviewEntity;
import catchtable.cooking.service.RestaurantService;
import catchtable.cooking.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final RestaurantService restaurantService;

    // 생성자 의존성 주입
    public ReviewController(ReviewService reviewService, RestaurantService restaurantService) {
        this.reviewService = reviewService;
        this.restaurantService = restaurantService;
    }


    // 하나의 식당(id)에 대한 리뷰 전체 조회 API
    @GetMapping("/review/{id}")
    public ResponseEntity<Result> readReviews(@PathVariable Long id) {
        List<ReviewEntity> reviewEntities = reviewService.readReviews(id);
        if (reviewEntities == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Result.res(HttpStatus.OK, HttpStatus.OK.toString(), reviewEntities));
    }

    // 하나의 식당 id 에 대한 리뷰 작성
    @PostMapping("/review/post/{id}")
    public ResponseEntity<Result> postReview(@PathVariable Long id, @RequestBody Review review) {
        reviewService.createReview(id, review);
        return ResponseEntity.ok(Result.res(HttpStatus.CREATED, HttpStatus.CREATED.toString(), review));
    }


}
