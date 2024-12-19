package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.MemberRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequest {

    @NotNull(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotNull(message = "나이는 필수 입력 값입니다.")
    private String email;

    @NotNull(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotNull(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotNull(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String phoneNumber;

    @NotNull(message = "고객 유형은 필수 선택 값입니다.")
    private MemberRole role;

}
