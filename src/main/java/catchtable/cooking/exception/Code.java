package catchtable.cooking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Code {

    OK(0, "success"),

    NOT_FOUND(4000, "페이지가 존재하지 않습니다."),
    RESTAURANT_ID_NOT_EXIST(4001, "식당 ID가 존재하지 않습니다."),
    REVIEW_Id_NOT_EXIST(4002, "리뷰 ID가 존재하지 않습니다."),
    INTER_SERVER_ERROR(4003, "COMMON-ERR-500");

    private final Integer code;
    private final String message;
    private final String data = null;

}
