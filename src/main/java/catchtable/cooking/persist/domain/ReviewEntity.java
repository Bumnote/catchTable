package catchtable.cooking.persist.domain;

import catchtable.cooking.model.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "REVIEW")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "content")
    private String content;


    // N : 1 = review : restaurant
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩으로 설정
    @JoinColumn(name = "restaurant_id") // 외래키 설정
    @JsonIgnore // ManyToOne
    private RestaurantEntity restaurantEntity;

    public ReviewEntity(RestaurantEntity restaurantEntity, Review review) {
        this.content = review.getContent();
        this.restaurantEntity = restaurantEntity;
    }


}
