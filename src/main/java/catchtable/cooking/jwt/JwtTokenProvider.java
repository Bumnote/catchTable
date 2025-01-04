package catchtable.cooking.jwt;

import catchtable.cooking.dto.Authentication;
import catchtable.cooking.dto.JwtToken;
import catchtable.cooking.dto.MemberJwtDTO;
import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    //권한 값을 인자로 받아와, 각각
    public JwtToken generateToken(Authentication authentication) {

        Date now = new Date();
        //만기 시간 설정
        Date accessTokenExpiresIn = new Date(now.getTime() + TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiresIn = new Date(now.getTime() + TOKEN_REFRESH_TIME);

        ObjectMapper mapper = new ObjectMapper();
        String memberJson = "";

        try {
            memberJson = mapper.writeValueAsString(authentication);
        } catch (JsonProcessingException e) {
            throw new CustomException(Code.ACCESS_TOKEN_UNAUTHORIZED);
        }

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
                .setSubject(memberJson)
                .setIssuer("cooking")
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

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
            throw new CustomException(Code.INVALID_ACCESS_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new CustomException(Code.EXPIRED_ACCESS_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new CustomException(Code.UNSUPPORTED_ACCESS_TOKEN);
        } catch (IllegalArgumentException e) {
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

    public MemberJwtDTO getSubject(final String token) {
        Claims claims = parseClaims(token);

        String memberJson = claims.getSubject();
        ObjectMapper mapper = new ObjectMapper();
        MemberJwtDTO memberJwtDTO = null;

        try {
            memberJwtDTO = mapper.readValue(memberJson, MemberJwtDTO.class);
        } catch (JsonProcessingException e) {
            throw new CustomException(Code.INVALID_ACCESS_TOKEN);
        }

        return memberJwtDTO;
    }

    public String getRole(final String token) {
        return getSubject(token).getRole().toString();
    }

}
