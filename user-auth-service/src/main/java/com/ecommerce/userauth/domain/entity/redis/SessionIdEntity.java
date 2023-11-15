package com.ecommerce.userauth.domain.entity.redis;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Shahinur Beg
 * Created date : 6/5/2023
 */
@Getter
@Setter
public class SessionIdEntity {
    private String sessionRedisKey;
    private String sessionId;
    private Long ttlTimeInSeconds;
}
