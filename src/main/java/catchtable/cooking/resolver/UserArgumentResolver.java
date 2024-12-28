package catchtable.cooking.resolver;

import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import catchtable.cooking.jwt.JwtTokenInterceptor;
import catchtable.cooking.jwt.JwtTokenProvider;
import catchtable.cooking.persist.domain.Member;
import catchtable.cooking.persist.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenInterceptor jwtTokenInterceptor;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Member.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Member resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = jwtTokenInterceptor.resolveToken(request);
        String nickname = jwtTokenProvider.getSubject(token);

        return memberRepository.findByNickname(nickname).orElseThrow(
                () -> new CustomException(Code.NOT_EXIST_NICKNAME)
        );
    }
}
