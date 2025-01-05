package catchtable.cooking.lock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LockUtil {

    private int waitTime;

    private int leaseTime;

    TimeUnit timeUnit;

}
