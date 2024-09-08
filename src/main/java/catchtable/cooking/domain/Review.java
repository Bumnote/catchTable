package catchtable.cooking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String content;


    // N : 1 = review : restaurant
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩으로 설정
    @JoinColumn(name = "restaurant_id") // 외래키 설정
    @JsonIgnore // ManyToOne
    private Restaurant restaurant;


}
