package catchtable.cooking.lock;

import catchtable.cooking.dto.ReservationCreateParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedissonLockProvider {

    private final RedissonClient redissonClient;

    public boolean getLock(ReservationCreateParam param, LockUtil lockUtil, Runnable onLockAcquired) {
        String key = generateRedisKey(param);
        RLock lock = redissonClient.getLock(key);

        try {
            if (lock.tryLock(lockUtil.getWaitTime(), lockUtil.getLeaseTime(), lockUtil.getTimeUnit())) {
                try {
                    onLockAcquired.run();
                    return true;
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return false;
    }

    private String generateRedisKey(ReservationCreateParam param) {
        Long id = param.getRestaurantId();
        String time = param.getReservationInfo().getTime().toString();
        return String.format("restaurant:%d:reservation:%s", id, time);
    }
}
