package catchtable.cooking.jwt;

import catchtable.cooking.exception.Code;
import catchtable.cooking.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws IOException {

        String token = resolveToken(request);
        String uri = request.getRequestURI();
        String role = jwtTokenProvider.getRole(token);

        log.info("access token: {}", token);
        log.info("request: {}", request);
        log.info("uri: {}", uri);
        log.info("role: {}", role);
        log.info("request path: {}", request.getServletPath());
        log.info("request header: {}", request.getHeader("Authorization"));
        log.info("request method: {}", request.getMethod());
        log.info("request contentType: {}", request.getContentType());
        log.info("request toString: {}", request.toString());

        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            log.info("validate token: pass");
            if (isAuthorized(uri, role)) {
                log.info("authorized");
                return true;
            }
        }

        response.setStatus(401);
        throw new CustomException(Code.ACCESS_TOKEN_UNAUTHORIZED);
    }

    private String resolveToken(HttpServletRequest request) {
        log.info("resolve 토큰 검증 request: {}", request);
        String bearerToken = request.getHeader(TOKEN_HEADER);

        log.info("resolve bearerToken: {}", bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            log.info("resolve 토큰 검증 완료!");
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    private boolean isAuthorized(String uri, String role) {
        if (uri.startsWith("/api/customers") && role.equals("CUSTOMER"))
            return true;
        else return uri.startsWith("/api/owners") && role.equals("OWNER");
    }
}
