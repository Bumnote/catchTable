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

    public LoginCreateParam of(LoginCreateRequest loginCreateRequest) {
        return LoginCreateParam.builder()
                .nickname(loginCreateRequest.getNickname())
                .password(loginCreateRequest.getPassword())
                .build();
    }

}
