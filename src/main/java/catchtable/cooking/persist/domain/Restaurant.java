package catchtable.cooking.persist.domain;

import catchtable.cooking.dto.RestaurantCreateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String address;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime deletedDateTime;

    public Restaurant(RestaurantCreateRequest restaurant) {
        this.name = restaurant.getName();
        this.phoneNumber = restaurant.getPhoneNumber();
        this.address = restaurant.getAddress();
    }

}
