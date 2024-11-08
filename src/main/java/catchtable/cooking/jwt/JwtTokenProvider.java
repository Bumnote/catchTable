package catchtable.cooking.jwt;

import catchtable.cooking.dto.Authentication;
import catchtable.cooking.dto.JwtToken;
import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Slf4j
@Component
public class JwtTokenProvider {

    private static final String BEARER_TYPE = "Bearer";
    private static final long TOKEN_EXPIRE_TIME = 1000 * 20;
    private static final long TOKEN_REFRESH_TIME = 1000 * 60 * 60 * 4;
    private final Key key;

    //생성자를 통하여 KEY 값을 BASE64로 디코딩(해석)하고 해석한 값을
    //SecretKey instance에 HMAC-SHA 로 암호화하여 초기화
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        log.info("key 암호화 시도");
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        log.info("key 암호화 성공");
    }

    //권한 값을 인자로 받아와, 각각
    public JwtToken generateToken(Authentication authentication) {
        log.info("토큰 생성 시작");

        Date now = new Date();
        //만기 시간 설정
        Date accessTokenExpiresIn = new Date(now.getTime() + TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiresIn = new Date(now.getTime() + TOKEN_REFRESH_TIME);

        log.info("Access 토큰 생성 시작!");
        /**
         * Access Token 생성
         *  header "alg" : "HS256"
         *  payload "auth": "CUSTOMER || OWNER"
         *  payload "sub": nickname
         *  payload "iss": "cooking"
         *  payload "iat": 토큰 발급 시간
         *  payload "exp" : 토큰 만료 시간
         */
        String accessToken = Jwts.builder()
                .setSubject(authentication.getNickname())
                .claim("auth", authentication.getRole())
                .setIssuer("cooking")
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        log.info("Access 토큰 생성 완료!");
        log.info("Refresh 토큰 생성 시작!");
        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        log.info("Refresh 토큰 생성 완료!");

        return JwtToken.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.", e);
            throw new CustomException(Code.INVALID_ACCESS_TOKEN);
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.", e);
            throw new CustomException(Code.EXPIRED_ACCESS_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.", e);
            throw new CustomException(Code.UNSUPPORTED_ACCESS_TOKEN);
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.", e);
            throw new CustomException(Code.WRONG_TYPE_ACCESS_TOKEN);
        }
    }

    //토큰을 claims로 변환하는 메서드
    public Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String getSubject(final String token) {
        return parseClaims(token).getSubject();
    }

}
