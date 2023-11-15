package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Shahinur Beg
 * Created date : 4/11/2023
 */
@AllArgsConstructor
@Getter
public enum ActivityTypeEnum {
    LOGIN("LG101", "Login"),
    LOGOUT("LG102", "Logout"),
    CHANGE_PIN("CP103", "Change Pin"),
    ;

    private final String code;
    private final String name;
}
