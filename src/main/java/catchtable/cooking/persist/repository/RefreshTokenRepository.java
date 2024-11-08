package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.RefreshToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RefreshTokenRepository(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(final RefreshToken refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getNickname(), refreshToken.getRefreshToken());
        redisTemplate.expire(refreshToken.getNickname(), 60, TimeUnit.SECONDS);
    }

    public Optional<RefreshToken> findByNickname(final String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String nickname = valueOperations.get(refreshToken);

        if (Objects.isNull(nickname)) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(refreshToken, nickname));
    }
}
