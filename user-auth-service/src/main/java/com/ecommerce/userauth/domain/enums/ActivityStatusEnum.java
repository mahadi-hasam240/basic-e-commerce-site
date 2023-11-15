package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActivityStatusEnum {
    INITIATE("1","Initiate"),
    SUCCESS("2","Success"),
    PENDING("3","Pending"),
    FAILED("4","Failed");


    private final String code;
    private final String Name;
}
