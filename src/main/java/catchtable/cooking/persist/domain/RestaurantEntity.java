package catchtable.cooking.persist.domain;

import catchtable.cooking.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "RESTAURANT")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id; // PK

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;


    @Column(name = "menu", nullable = false)
    private String menu;

    // 1 : N = restaurant : review
    // @JsonIgnore
    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews = new ArrayList<>(); // 리뷰의 목록


    // restaurant 객체를 받을 있는 생성자
    public RestaurantEntity(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.phoneNumber = restaurant.getPhoneNumber();
        this.address = restaurant.getAddress();
        this.menu = restaurant.getMenu();
    }

}
