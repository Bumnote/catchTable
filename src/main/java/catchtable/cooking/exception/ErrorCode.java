package catchtable.cooking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND(404, "페이지가 존재하지 않습니다.", "PAGE NOT FOUND"),
    RESTAURANT_ID_NOT_EXIST(404, "식당 ID가 존재하지 않습니다.", "RESTAURANT ID NOT EXIST"),
    REVIEW_Id_NOT_EXIST(404, "리뷰 ID가 존재하지 않습니다.", "REVIEW ID NOT EXIST"),
    INTER_SERVER_ERROR(500, "COMMON-ERR-500", "INTER SERVER ERROR");

    private int status;
    private String code;
    private String message;

}
