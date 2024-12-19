package catchtable.cooking.controller;

import catchtable.cooking.dto.*;
import catchtable.cooking.exception.Code;
import catchtable.cooking.persist.domain.Member;
import catchtable.cooking.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/test")
    public CommonResponse<?> test(@UserArg Member member) {
        return CommonResponse.of(member);
    }


}
