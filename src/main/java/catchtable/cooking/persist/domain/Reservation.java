package catchtable.cooking.persist.domain;

import catchtable.cooking.dto.ReservationCreateParam;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private Integer personCount;

    private final Integer reservationCapacity = 8;

    private LocalDate reservationDate;

    private LocalTime reservationTime;

    private LocalDateTime deletedDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Reservation(ReservationCreateParam param, Member member, Restaurant restaurant) {
        this.personCount = param.getReservationInfo().getPersonCount();
        this.reservationDate = param.getReservationInfo().getDate();
        this.reservationTime = param.getReservationInfo().getTime();
        this.member = member;
        this.restaurant = restaurant;
    }

}
