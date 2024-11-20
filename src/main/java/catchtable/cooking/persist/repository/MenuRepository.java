package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.Menu;
import catchtable.cooking.persist.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByRestaurant(Restaurant restaurant);

}
