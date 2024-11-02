package catchtable.cooking.persist.domain;

import catchtable.cooking.dto.RestaurantCreateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Waiting> waitings = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    public Restaurant(RestaurantCreateRequest restaurant) {
        this.name = restaurant.getName();
        this.phoneNumber = restaurant.getPhoneNumber();
        this.address = restaurant.getAddress();
    }

}
