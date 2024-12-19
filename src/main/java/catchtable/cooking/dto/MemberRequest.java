package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.MemberType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {

    private String name;

    private String email;

    private String nickname;

    private String password;

    private String phoneNumber;

    private MemberType role;

}
