package com.ecommerce.userauth.repository.redis;

import com.ecommerce.userauth.domain.entity.redis.TokenEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisTokenRepository {

    private final RedisTemplate<String, String> template;

    public RedisTokenRepository(@Qualifier("redisTemplate") RedisTemplate<String, String> template) {
        this.template = template;
    }

    public void save(TokenEntity entity) {
        template.opsForValue().set(entity.getUserName(), entity.getToken(), entity.getTtlTimeInSeconds(), TimeUnit.SECONDS);
    }
    public String get(String key) {
        return template.opsForValue().get(key);
    }

    public void delete(String key){
        template.delete(key);
    }

}
