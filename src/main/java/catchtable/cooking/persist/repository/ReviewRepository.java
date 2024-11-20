package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.persist.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByRestaurant(Restaurant restaurant);

}
