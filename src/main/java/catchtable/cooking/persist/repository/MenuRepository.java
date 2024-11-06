package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByRestaurantId(Long restaurantId);

}
