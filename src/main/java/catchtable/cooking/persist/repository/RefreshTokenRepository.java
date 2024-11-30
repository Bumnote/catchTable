package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final RedisTemplate<Long, String> redisTemplate;

    public void save(final RefreshToken refreshToken) {
        ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getId(), refreshToken.getRefreshToken());
        redisTemplate.expire(refreshToken.getId(), 60, TimeUnit.SECONDS);
    }

    public Optional<RefreshToken> findById(final Long id) {
        ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
        String refreshToken = valueOperations.get(id);

        if (Objects.isNull(refreshToken)) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(id, refreshToken));
    }

    public void delete(final Long id) {
        redisTemplate.delete(id);
    }
}
