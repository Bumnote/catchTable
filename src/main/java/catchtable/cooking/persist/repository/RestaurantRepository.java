package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {

    List<Restaurant> findAllByName(String name);

}
