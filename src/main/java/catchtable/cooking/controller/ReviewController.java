package catchtable.cooking.controller;

import catchtable.cooking.dto.HttpResponse;
import catchtable.cooking.dto.ReviewCreateRequest;
import catchtable.cooking.exception.IdNotExistException;
import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.persist.domain.Review;
import catchtable.cooking.persist.repository.RestaurantRepository;
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

    @GetMapping("/restaurants/{id}/reviews")
    public ResponseEntity<HttpResponse> readReviews(@PathVariable Long id) {
        List<Review> reviews = reviewService.readReviews(id);

        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK, HttpStatus.OK.toString(), reviews));
    }

    @PostMapping("/restaurants/{id}/reviews")
    public ResponseEntity<HttpResponse> postReview(@PathVariable Long id, @RequestBody ReviewCreateRequest reviewCreateRequest) {
        reviewService.createReview(id, reviewCreateRequest);
        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK, HttpStatus.OK.toString(), reviewCreateRequest));
    }

    @DeleteMapping("/restaurants/{restaurantId}/reviews/{reviewId}")
    public ResponseEntity<HttpResponse> deleteReview(@PathVariable Long restaurantId, @PathVariable Long reviewId) {
        reviewService.deleteReview(restaurantId, reviewId);
        return ResponseEntity.ok(HttpResponse.res(HttpStatus.OK, HttpStatus.OK.toString()));
    }


}
