package catchtable.cooking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ReservationInfo {

    private Integer personCount;

    private LocalDate date;

}
