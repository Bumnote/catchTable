package catchtable.cooking.controller;

import catchtable.cooking.aspect.AuthRequired;
import catchtable.cooking.dto.*;
import catchtable.cooking.exception.Code;
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

    @PostMapping("/api/members/signup")
    public CommonResponse<?> signup(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest) {
        memberService.register(new MemberSignUpParam().of(memberSignUpRequest));
        return CommonResponse.of(Code.OK);
    }

    @PostMapping("/api/members/login")
    public CommonResponse<?> login(@Valid @RequestBody LoginCreateRequest loginCreateRequest) {
        JwtToken jwtToken = memberService.authenticate(new LoginCreateParam().of(loginCreateRequest));
        return CommonResponse.of(jwtToken);
    }

    @PostMapping("/api/members/logout")
    public CommonResponse<?> logout(@RequestHeader("Authorization") String token) {

        memberService.logout(token);
        return CommonResponse.of(Code.OK);
    }


    @PostMapping("/api/members/reissue")
    public CommonResponse<?> reissue(@RequestBody TokenRequest tokenRequest,
                                     HttpServletResponse response) {

        JwtToken jwtToken = memberService.reissue(new TokenCreateParam().of(tokenRequest), response);

        return CommonResponse.of(jwtToken);
    }

    @AuthRequired(role = "CUSTOMER")
    @GetMapping("/api/test")
    public CommonResponse<?> test(MemberJwtDTO memberJwtDTO) {
        return CommonResponse.of(memberJwtDTO);
    }
}
