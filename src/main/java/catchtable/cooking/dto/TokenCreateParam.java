package catchtable.cooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenCreateParam {

    private String accessToken;

    private String refreshToken;

    public TokenCreateParam of(TokenRequest request) {
        return TokenCreateParam.builder()
                .accessToken(request.getAccessToken())
                .refreshToken(request.getRefreshToken())
                .build();
    }
}
