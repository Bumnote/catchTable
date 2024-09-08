package catchtable.cooking.service;

import catchtable.cooking.domain.Restaurant;
import catchtable.cooking.domain.Review;
import catchtable.cooking.repository.RestaurantRepository;
import catchtable.cooking.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    // 생성자 의존성 주입
    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    // 리뷰 작성
    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    // 하나의 식당에 대한 전체 리뷰 리스트 return
    public List<Review> readReviews(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant.getReviews() == null) {
            return null;
        } else {
            return restaurant.getReviews();
        }
    }


}
