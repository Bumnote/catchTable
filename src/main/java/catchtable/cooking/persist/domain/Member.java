package catchtable.cooking.persist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String nickname;

    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime deletedDateTime;

}