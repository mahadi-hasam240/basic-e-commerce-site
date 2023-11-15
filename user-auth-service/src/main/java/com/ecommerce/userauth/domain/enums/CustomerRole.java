package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomerRole {
    ROLE_USER(1,"ROLE_USER"),
    ROLE_BASIC(2,"ROLE_BASIC");

    private Integer roleCode;
    private String role;
}
