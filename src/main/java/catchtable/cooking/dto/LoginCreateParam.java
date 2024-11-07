package catchtable.cooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginCreateParam {

    private String nickname;

    private String password;

    public LoginCreateParam of(LoginRequest loginRequest) {
        return LoginCreateParam.builder()
                .nickname(loginRequest.getNickname())
                .password(loginRequest.getPassword())
                .build();
    }

}
