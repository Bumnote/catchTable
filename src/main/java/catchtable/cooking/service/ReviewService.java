package catchtable.cooking.service;

import catchtable.cooking.dto.ReviewCreateRequest;
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


    // 하나의 식당에 대한 전체 리뷰 리스트 return
    public List<Review> readReviews(Long id) {
        Optional<Restaurant> restaurantEntity = restaurantRepository.findById(id);
        if (restaurantEntity.isPresent() && restaurantEntity.get().getReviews() != null) {
            return restaurantEntity.get().getReviews();
        } else {
            return null;
        }
    }

    // 리뷰 작성
    public void createReview(Long id, ReviewCreateRequest reviewCreateRequest) {
        Optional<Restaurant> restaurantEntity = restaurantRepository.findById(id);

        if (restaurantEntity.isPresent()) {
            Review review = new Review(restaurantEntity.get(), reviewCreateRequest);
            restaurantEntity.get().getReviews().add(review);

            restaurantRepository.save(restaurantEntity.get());
            reviewRepository.save(review);
        }


    }
}
