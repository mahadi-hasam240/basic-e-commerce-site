package com.ecommerce.userauth.domain.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@Setter
@RedisHash("message")
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    @Id
    private String id;
    @Indexed
    private String locale;
    @Indexed
    private String key;
    private String content;
}
