package catchtable.cooking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull(message = "이름은 필수 입력 값입니다.")
    private String nickname;

    @NotNull(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

}
