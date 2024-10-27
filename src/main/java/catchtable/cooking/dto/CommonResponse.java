package catchtable.cooking.dto;

import catchtable.cooking.exception.Code;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {

    private final Integer code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;


    public static <T> CommonResponse<T> of(T data) {
        return new CommonResponseBuilder<T>()
                .code(Code.OK.getCode())
                .message(Code.OK.getMessage())
                .data(data).build();
    }

    public static CommonResponse<String> of(Code errorCode) {
        return new CommonResponseBuilder<String>()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(null).build();
    }

}
