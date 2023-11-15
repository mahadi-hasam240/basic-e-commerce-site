package com.ecommerce.userauth.repository.redis;

import com.ecommerce.userauth.domain.entity.redis.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {
    Optional<Message> findByKeyAndLocale(String key, String locale);
}
