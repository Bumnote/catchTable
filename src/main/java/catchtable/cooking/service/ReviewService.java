package catchtable.cooking.service;

import catchtable.cooking.dto.ReviewCreateRequest;
import catchtable.cooking.exception.ErrorCode;
import catchtable.cooking.exception.IdNotExistException;
import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.persist.domain.Review;
import catchtable.cooking.persist.repository.RestaurantRepository;
import catchtable.cooking.persist.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Review> readReviews(Long id) {
        Optional<Restaurant> restaurantEntity = restaurantRepository.findById(id);
        if (restaurantEntity.isPresent() && restaurantEntity.get().getReviews() != null) {
            return restaurantEntity.get().getReviews();
        } else {
            return null;
        }
    }

    public void createReview(Long id, ReviewCreateRequest reviewCreateRequest) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        if (restaurant.isPresent()) {
            Review review = new Review(restaurant.get(), reviewCreateRequest);
            reviewRepository.save(review);
        }

    }

    public void deleteReview(Long restaurantId, Long reviewId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IdNotExistException("해당 식당이 존재하지 않습니다.", ErrorCode.RESTAURANT_ID_NOT_EXIST));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IdNotExistException("해당 리뷰가 존재하지 않습니다.", ErrorCode.REVIEW_Id_NOT_EXIST));
        reviewRepository.delete(review);
    }
}
