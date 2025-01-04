package catchtable.cooking.resolver;

import catchtable.cooking.dto.MemberJwtDTO;
import catchtable.cooking.jwt.JwtTokenInterceptor;
import catchtable.cooking.jwt.JwtTokenProvider;
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

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return MemberJwtDTO.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public MemberJwtDTO resolveArgument(MethodParameter parameter,
                                        ModelAndViewContainer mavContainer,
                                        NativeWebRequest webRequest,
                                        WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = jwtTokenInterceptor.resolveToken(request);

        return jwtTokenProvider.getSubject(token);
    }
}
