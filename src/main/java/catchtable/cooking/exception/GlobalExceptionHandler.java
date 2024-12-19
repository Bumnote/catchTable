package catchtable.cooking.exception;

import catchtable.cooking.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected CommonResponse<String> handleException(CustomException e) {
        return CommonResponse.of(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected CommonResponse<String> handleException(MethodArgumentNotValidException e) {
        return CommonResponse.of(Code.NOT_NULL);
    }

}
