package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.Waiting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaitingItemResponse {

    private Long id;

    private String status;

    private Long personCount;

    public WaitingItemResponse(Waiting waiting) {
        this.id = waiting.getId();
        this.status = waiting.getStatus();
        this.personCount = waiting.getPersonCount();
    }

    public static WaitingItemResponse of(Waiting waiting) {
        return WaitingItemResponse.builder()
                .id(waiting.getId())
                .status(waiting.getStatus())
                .personCount(waiting.getPersonCount())
                .build();
    }

}
