package catchtable.cooking.controller;

import catchtable.cooking.domain.Restaurant;
import catchtable.cooking.domain.Review;
import catchtable.cooking.dto.ReviewDto;
import catchtable.cooking.repository.RestaurantRepository;
import catchtable.cooking.repository.ReviewRepository;
import catchtable.cooking.service.RestaurantService;
import catchtable.cooking.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    // 생성자 의존성 주입
    public ReviewController(ReviewService reviewService, RestaurantRepository restaurantRepository, RestaurantService restaurantService) {
        this.reviewService = reviewService;
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
    }


    // 하나의 식당에 대한 리뷰 전체 조회 API
    @GetMapping("/read/reviews/{id}")
    public List<Review> readReviews(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.get().getReviews();
    }

    // 하나의 식당 id 에 대한 리뷰 작성
    @PostMapping("/create/reviews/{id}")
    public void postReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        Review review = Review
                .builder()
                .restaurant(restaurantRepository.findById(id).isPresent() ? restaurantRepository.findById(id).get() : null)
                .content(reviewDto.getContent())
                .build();

        reviewService.createReview(review);
    }


}
