package catchtable.cooking.lock;

import catchtable.cooking.dto.ReservationCreateRequest;
import catchtable.cooking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedissonLockReservation {

    private final RedissonClient redissonClient;

    private final ReservationService reservationService;

    public void createReservation(ReservationCreateRequest request) {

        RLock lock = redissonClient.getLock(request.getCustomerId().toString());

        try {
            boolean available = lock.tryLock(20, 1, TimeUnit.SECONDS);

            if (!available) {
                log.info("redisson lock 획득 실패");
                return;
            }

            reservationService.createReservation(request);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
