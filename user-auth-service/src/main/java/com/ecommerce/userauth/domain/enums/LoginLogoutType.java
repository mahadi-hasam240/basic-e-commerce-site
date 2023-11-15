package com.ecommerce.userauth.domain.enums;

public enum LoginLogoutType {
    LOGIN("LI"),
    LOGOUT("LO");

    private String code;

    LoginLogoutType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
