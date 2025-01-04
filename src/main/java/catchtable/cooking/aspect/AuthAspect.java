package catchtable.cooking.aspect;

import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import catchtable.cooking.jwt.JwtTokenInterceptor;
import catchtable.cooking.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthAspect {

    private final HttpServletRequest request;
    private final JwtTokenInterceptor jwtTokenInterceptor;
    private final JwtTokenProvider jwtTokenProvider;

    @Before("@annotation(AuthRequired)")
    public void checkAuthentication(JoinPoint joinPoint) {

        String token = jwtTokenInterceptor.resolveToken(request);
        if (StringUtils.isNotBlank(token) && jwtTokenProvider.validateToken(token)) {
            log.info("validate token: pass");
        }

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        if (method.isAnnotationPresent(AuthRequired.class)) {

            AuthRequired authRequired = method.getAnnotation(AuthRequired.class);

            String requiredRole = authRequired.role();
            String tokenRole = jwtTokenProvider.getRole(token);

            if (!StringUtils.equals(requiredRole, tokenRole)) {
                throw new CustomException(Code.ACCESS_TOKEN_UNAUTHORIZED);
            }
        }
    }


}
