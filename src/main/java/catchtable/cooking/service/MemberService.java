package catchtable.cooking.service;

import catchtable.cooking.dto.*;
import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import catchtable.cooking.jwt.JwtTokenProvider;
import catchtable.cooking.persist.domain.Member;
import catchtable.cooking.persist.domain.RefreshToken;
import catchtable.cooking.persist.repository.MemberRepository;
import catchtable.cooking.persist.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    public void register(MemberCreateParam param) {

        MemberNicknameValidate(param.getNickname());
        MemberPassWordValidate(param.getPassword());
        MemberPhoneNumberValidate(param.getPhoneNumber());

        Member member = Member.builder()
                .name(param.getName())
                .email(param.getEmail())
                .nickname(param.getNickname())
                .password(new BCryptPasswordEncoder().encode(param.getPassword()))
                .phoneNumber(param.getPhoneNumber())
                .role(param.getRole())
                .build();

        memberRepository.save(member);
    }

    public JwtToken authenticate(LoginCreateParam param) {

        Member member = memberRepository.findByNickname(param.getNickname()).orElseThrow(
                () -> new CustomException(Code.NOT_EXIST_NICKNAME)
        );

        if (!new BCryptPasswordEncoder().matches(param.getPassword(), member.getPassword())) {
            throw new CustomException(Code.UNMATCHED_PASSWORD);
        }

        JwtToken jwtToken = jwtTokenProvider.generateToken(new Authentication().of(member));

        RefreshToken refreshToken = RefreshToken.builder()
                .key(member.getId())
                .value(jwtToken.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        return jwtToken;
    }

    public void logout(String token) {

        if (!jwtTokenProvider.validateToken(token)) {
            throw new CustomException(Code.ACCESS_TOKEN_UNAUTHORIZED);
        }

        String nickname = jwtTokenProvider.getSubject(token);
        log.info("logout nickname check: {}", nickname);

        Member member = memberRepository.findByNickname(nickname).orElseThrow(
                () -> new CustomException(Code.NOT_EXIST_NICKNAME));

        refreshTokenRepository.delete(member.getId());
    }

    public JwtToken reissue(TokenCreateParam param, HttpServletResponse response) {

        // 1. Refersh Token 검증
        if (!jwtTokenProvider.validateToken(param.getRefreshToken())) {
            throw new CustomException(Code.REFRESH_TOKEN_UNAUTHORIZED);
        }

        log.info("accessToken: {}", param.getAccessToken());
        log.info("refreshToken: {}", param.getRefreshToken());
        // 2. Access Token 에서 nickname 가져오고, set
        String nickname = jwtTokenProvider.getSubject(param.getAccessToken());
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException(Code.NOT_EXIST_NICKNAME));

        log.info("nickname : {}", nickname);

        // 3. 저장소에서 nickname 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findById(member.getId())
                .orElseThrow(() -> new CustomException(Code.EXPIRED_REFRESH_TOKEN));

        log.info("refreshToken : {}", refreshToken.getRefreshToken());
        log.info("refreshToken key : {}", refreshToken.getKey());

        // 4. Refresh Token 일치 검사
        if (!refreshToken.getValue().equals(param.getRefreshToken())) {
            throw new CustomException(Code.REFRESH_TOKEN_UNMATCHED);
        }

        // 5. 새로운 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(new Authentication().of(member));

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(jwtToken.getRefreshToken());

        refreshTokenRepository.save(newRefreshToken);

        // 7. 토큰 발급
        return jwtToken;

    }

    private void MemberNicknameValidate(String nickname) {
        if (nickname.length() < 2 || nickname.length() > 12) {
            throw new CustomException(Code.INVALID_LENGTH_NICKNAME);
        }
    }

    private void MemberPassWordValidate(String password) {
        if (password.length() < 8 || password.length() > 15) {
            throw new CustomException(Code.INVALID_LENGTH_PASSWORD);
        }
    }

    private void MemberPhoneNumberValidate(String phoneNumber) {
        String phonePattern = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$";
        if (!phoneNumber.matches(phonePattern)) {
            throw new CustomException(Code.INVALID_PHONE_NUMBER);
        }
    }
}
