package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewItemResponse {

    private Long id;

    private String content;

    public static ReviewItemResponse of(Review review) {
        return ReviewItemResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .build();
    }

}
