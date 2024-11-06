package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByRestaurantId(Long restaurantId);

}
