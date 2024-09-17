package catchtable.cooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class HttpResult<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public HttpResult(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static <T> HttpResult<T> res(final HttpStatus status, final String message) {
        return res(status, message, null);
    }

    public static <T> HttpResult<T> res(final HttpStatus status, final String message, final T data) {
        return HttpResult.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }


}
