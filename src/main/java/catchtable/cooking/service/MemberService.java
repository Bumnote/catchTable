package catchtable.cooking.service;

import catchtable.cooking.dto.Authentication;
import catchtable.cooking.dto.JwtToken;
import catchtable.cooking.dto.LoginCreateParam;
import catchtable.cooking.dto.MemberCreateParam;
import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import catchtable.cooking.jwt.JwtTokenProvider;
import catchtable.cooking.persist.domain.Member;
import catchtable.cooking.persist.repository.MemberRepository;
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

        return jwtTokenProvider.generateToken(new Authentication().of(member));
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
