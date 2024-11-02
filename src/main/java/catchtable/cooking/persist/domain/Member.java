package catchtable.cooking.persist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String name;

    private String phoneNumber;

    private String email;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime deletedDateTime;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Waiting> waitings = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

}
