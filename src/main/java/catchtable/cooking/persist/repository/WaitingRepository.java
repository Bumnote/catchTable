package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.Restaurant;
import catchtable.cooking.persist.domain.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaitingRepository extends JpaRepository<Waiting, Long> {

    List<Waiting> findAllByRestaurant(Restaurant restaurant);

}
