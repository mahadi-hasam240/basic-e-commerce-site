package com.ecommerce.userauth.service.redis;

import com.ecommerce.userauth.domain.entity.redis.TokenEntity;
import com.ecommerce.userauth.repository.redis.RedisTokenRepository;
import com.ecommerce.userauth.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisTokenService extends BaseService {

    public static final String REDIS_TOKEN_VALIDITY_IN_MINUTES = "30";
    private final RedisTokenRepository repository;

    public void pushToken(TokenEntity entity) {
        Long tokenExpiryTime = Long.parseLong(REDIS_TOKEN_VALIDITY_IN_MINUTES) * 60;
        entity.setTtlTimeInSeconds(tokenExpiryTime);
        repository.save(entity);
    }

    public String getToken(String key) {
        return repository.get(key);
    }

    public void deleteToken(String key){
        repository.delete(key);
    }

}
