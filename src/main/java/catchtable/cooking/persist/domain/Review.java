package catchtable.cooking.persist.domain;

import catchtable.cooking.dto.ReviewCreateRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "REVIEW")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    private Long createdBy;

    private Long updatedBy;

    // N : 1 = review : restaurant
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩으로 설정
    @JoinColumn(name = "restaurant_id") // 외래키 설정
    @JsonIgnore // ManyToOne
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Review(Restaurant restaurant, ReviewCreateRequest reviewCreateRequest) {
        this.content = reviewCreateRequest.getContent();
        this.restaurant = restaurant;
    }


}
