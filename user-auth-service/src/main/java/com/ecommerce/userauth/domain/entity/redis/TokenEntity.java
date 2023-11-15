package com.ecommerce.userauth.domain.entity.redis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TokenEntity implements Serializable {
    private String token;
    private String userName;
    private Long ttlTimeInSeconds;
}
