package catchtable.cooking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id; // PK

    private String name;
    @Column(name = "phone_number", nullable = false)
    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;
    private String menu;

    // 1 : N = restaurant : review
    // @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews = new ArrayList<>(); // 리뷰의 목록

}
