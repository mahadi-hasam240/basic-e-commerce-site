package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Iabur on January 22, 2023.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum NotificationTypeEnum {
    SMS(101, "SMS"),
    EMAIL(102, "EMAIL");


    private Integer code;
    private String type;

}
