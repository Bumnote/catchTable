package catchtable.cooking.controller;

import catchtable.cooking.dto.*;
import catchtable.cooking.exception.Code;
import catchtable.cooking.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/member/signup")
    public CommonResponse<?> signup(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest) {
        memberService.register(new MemberSignUpParam().of(memberSignUpRequest));
        return CommonResponse.of(Code.OK);
    }

    @PostMapping("/api/member/login")
    public CommonResponse<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtToken jwtToken = memberService.authenticate(new LoginCreateParam().of(loginRequest));
        return CommonResponse.of(jwtToken);
    }

    @PostMapping("/api/member/logout")
    public CommonResponse<?> logout(@RequestHeader("Authorization") String token) {

        memberService.logout(token);
        return CommonResponse.of(Code.OK);
    }


    @PostMapping("/api/member/reissue")
    public CommonResponse<?> reissue(@RequestBody TokenRequest tokenRequest,
                                     HttpServletResponse response) {

        log.info("response: {}", response);
        log.info("response: {}", response.getStatus());
        log.info("response: {}", response.getContentType());
        JwtToken jwtToken = memberService.reissue(new TokenCreateParam().of(tokenRequest), response);

        return CommonResponse.of(jwtToken);
    }

    @PostMapping("/api/test")
    public CommonResponse<?> test(@RequestHeader("Authorization") String token) {
        log.info("token: {}", token);
        return CommonResponse.of(token);
    }


}
