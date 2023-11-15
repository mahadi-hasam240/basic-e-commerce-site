package com.ecommerce.userauth.repository.redis;

import com.ecommerce.userauth.domain.entity.redis.SessionIdEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class SessionIdRepository {

    private final RedisTemplate<String, String> template;

    public SessionIdRepository(@Qualifier("redisTemplate") RedisTemplate<String, String> template) {
        this.template = template;
    }

    public void save(SessionIdEntity entity) {
        template.opsForValue().set(entity.getSessionRedisKey(), entity.getSessionId(), entity.getTtlTimeInSeconds(), TimeUnit.SECONDS);
    }
    public String get(String key) {
        return template.opsForValue().get(key);
    }

    public void delete(String key){
        template.delete(key);
    }

}
