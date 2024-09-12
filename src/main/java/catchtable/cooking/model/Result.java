package catchtable.cooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class Result<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public Result(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static <T> Result<T> res(final HttpStatus status, final String message) {
        return res(status, message, null);
    }

    public static <T> Result<T> res(final HttpStatus status, final String message, final T data) {
        return Result.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }


}
