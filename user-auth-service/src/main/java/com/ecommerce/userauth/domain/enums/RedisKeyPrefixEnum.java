package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Shahinur Beg
 * Created date : 6/6/2023
 */
@Getter
@AllArgsConstructor
public enum RedisKeyPrefixEnum {
    DEVICE_IDENTIFIER("DI"),
    SESSION_ID("SI");

    private final String prefix;
}
