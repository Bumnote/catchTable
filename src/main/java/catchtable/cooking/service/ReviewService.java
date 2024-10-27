package catchtable.cooking.service;

import catchtable.cooking.dto.ReviewCreateParam;
import catchtable.cooking.dto.ReviewCreateRequest;
import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.persist.domain.Review;
import catchtable.cooking.persist.repository.RestaurantRepository;
import catchtable.cooking.persist.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
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

    public void updateReview(Long restaurantId, Long reviewId, ReviewCreateParam reviewCreateParam) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(Code.RESTAURANT_ID_NOT_EXIST));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(Code.REVIEW_Id_NOT_EXIST));

        Review reviewParam = Review.builder()
                .id(review.getId())
                .content(reviewCreateParam.getContent())
                .restaurant(restaurant).build();

        reviewRepository.save(reviewParam);
    }

    public void deleteReview(Long restaurantId, Long reviewId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(Code.RESTAURANT_ID_NOT_EXIST));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(Code.REVIEW_Id_NOT_EXIST));
        reviewRepository.delete(review);
    }
}
