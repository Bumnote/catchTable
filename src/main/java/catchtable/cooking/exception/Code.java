package catchtable.cooking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Code {

    OK(0, "success"),

    // Sign up Exception Code
    SIGN_UP_FAILED(1000, "회원가입에 실패했습니다."),
    LOGIN_FAILED(1001, "로그인에 실패했습니다."),
    INVALID_LENGTH_NICKNAME(1002, "닉네임은 2자 ~ 12까지 가능합니다."),
    INVALID_LENGTH_PASSWORD(1003, "비밀번호는 8자 ~ 15자까지 가능합니다."),
    INVALID_PHONE_NUMBER(1004, "휴대폰 번호 형식이 잘못되었습니다."),
    ALREADY_EXIST_NICKNAME(1005, "이미 사용중인 닉네임입니다."),

    // JWT Token Exception Code
    INVALID_ACCESS_TOKEN(2000, "잘못된 ACCESS_JWT 서명입니다."),
    EXPIRED_ACCESS_TOKEN(2001, "만료된 ACCESS_JWT 토큰입니다."),
    INVALID_REFRESH_TOKEN(2002, "잘못된 REFRESH_JWT 서명입니다."),
    EXPIRED_REFRESH_TOKEN(2003, "만료된 REFRESH_JWT 토큰입니다."),
    ACCESS_TOKEN_UNAUTHORIZED(2004, "ACCESS_TOKEN: 유효한 자격증명을 제공하지 않습니다."),
    REFRESH_TOKEN_UNAUTHORIZED(2005, "REFRESH_TOKEN: 유효한 자격증명을 제공하지 않습니다."),
    ACCESS_TOKEN_UNMATCHED(2006, "ACCESS_TOKEN: 일치하지 않습니다."),
    REFRESH_TOKEN_UNMATCHED(2007, "REFRESH_TOKEN: 일치하지 않습니다."),
    UNSUPPORTED_ACCESS_TOKEN(2008, "지원되지 않는 JWT 토큰입니다."),
    WRONG_TYPE_ACCESS_TOKEN(2009, "JWT 토큰이 잘못되었습니다."),

    // Restaurant Exception Code
    RESTAURANT_ID_NOT_EXIST(3001, "식당 ID가 존재하지 않습니다."),

    // Review Exception Code
    REVIEW_Id_NOT_EXIST(3002, "리뷰 ID가 존재하지 않습니다.");

    // Reservation Exception Code

    private final Integer code;
    private final String message;
    private final String data = null;

}
