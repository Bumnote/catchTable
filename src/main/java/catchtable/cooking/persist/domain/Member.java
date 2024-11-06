package catchtable.cooking.persist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String name;

    private String phoneNumber;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberType role;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime deletedDateTime;


}
