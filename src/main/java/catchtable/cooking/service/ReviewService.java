package catchtable.cooking.service;

import catchtable.cooking.model.Review;
import catchtable.cooking.persist.domain.RestaurantEntity;
import catchtable.cooking.persist.domain.ReviewEntity;
import catchtable.cooking.persist.repository.RestaurantRepository;
import catchtable.cooking.persist.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    // 생성자 의존성 주입
    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    // 하나의 식당에 대한 전체 리뷰 리스트 return
    public List<ReviewEntity> readReviews(Long id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        if (restaurantEntity.isPresent() && restaurantEntity.get().getReviews() != null) {
            return restaurantEntity.get().getReviews();
        } else {
            return null;
        }
    }

    // 리뷰 작성
    public void createReview(Long id, Review review) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);

        if (restaurantEntity.isPresent()) {
            ReviewEntity reviewEntity = new ReviewEntity(restaurantEntity.get(), review);
            restaurantEntity.get().getReviews().add(reviewEntity);

            restaurantRepository.save(restaurantEntity.get());
            reviewRepository.save(reviewEntity);
        }


    }
}
