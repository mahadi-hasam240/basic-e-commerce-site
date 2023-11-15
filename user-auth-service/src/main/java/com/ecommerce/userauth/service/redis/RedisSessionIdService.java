package com.ecommerce.userauth.service.redis;

import com.ecommerce.userauth.domain.entity.redis.SessionIdEntity;
import com.ecommerce.userauth.repository.redis.SessionIdRepository;
import com.ecommerce.userauth.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSessionIdService extends BaseService {

    private final SessionIdRepository repository;

    public void pushSessionId(SessionIdEntity entity) {
        Long tokenExpiryTime = Long.parseLong(jwtExpiryTime) * 60;
        entity.setTtlTimeInSeconds(tokenExpiryTime);
        repository.save(entity);
    }

    public String getSession(String key) {
        return repository.get(key);
    }

    public void deleteSession(String key){
        repository.delete(key);
    }

}
