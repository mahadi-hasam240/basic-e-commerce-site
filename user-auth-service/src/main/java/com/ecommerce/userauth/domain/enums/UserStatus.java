package com.ecommerce.userauth.domain.enums;

public enum UserStatus {

    ACTIVE(1, "ACTIVE"),
    FORCE_PASSWORD_CHANGED(2, "PASSWORD NEED TO RESET"),
    ACCOUNT_RESTRICTED(3, "ACCOUNT RESTRICTED"),
    ACCOUNT_TEMPORARY_BLOCK(4, "ACCOUNT TEMPORARY BLOCKED"),
    ACCOUNT_CLOSED(5, "ACCOUNT INACTIVE"),
    PASSWORD_EXPIRED(6, "PASSWORD EXPIRED"),
    BASIC_CUSTOMER(7, "BASIC CUSTOMER"),
    UNAUTHORIZED_CUSTOMER(8, "UNAUTHORIZED CUSTOMER"),
    CUSTOMER_PIN_NOT_SET(9, "Customer PIN Customer"),
    UNAUTHORIZED_CUSTOMER_FORCE_PASSWORD_CHANGE(10, "Unauthorized customer force password change"),
    PASSWORD_NEED_RESET(12, "Customer Password Pin Reset");

    private int code;
    private String text;

    UserStatus(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static boolean isCustomerBasicUser(int status) {
        return UserStatus.BASIC_CUSTOMER.getCode() == status;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static boolean isCustomerTemporarilyBlocked(int status) {
        return UserStatus.ACCOUNT_TEMPORARY_BLOCK.getCode() == status;
    }

    public static boolean isCustomerActive(int status) {
        return UserStatus.ACTIVE.getCode() == status;
    }

    public static boolean isCustomerPasswordExpired(int status) {
        return UserStatus.PASSWORD_EXPIRED.getCode() == status;
    }

    public static boolean isCustomerEligibleForForcePasswordChange(int status) {
        return UserStatus.FORCE_PASSWORD_CHANGED.getCode() == status || UserStatus.PASSWORD_EXPIRED.getCode() == status;
    }

    public static boolean isAccountRestricted(int status) {
        return UserStatus.ACCOUNT_RESTRICTED.getCode() == status;
    }

    public static boolean isAccountClosed(int status) {
        return UserStatus.ACCOUNT_CLOSED.getCode() == status;
    }

    public static boolean isAccountAccessAble(int status) {
        return (UserStatus.BASIC_CUSTOMER.getCode() == status
                || UserStatus.UNAUTHORIZED_CUSTOMER.getCode() == status
                || UserStatus.CUSTOMER_PIN_NOT_SET.getCode() == status
                || UserStatus.FORCE_PASSWORD_CHANGED.getCode() == status
                || UserStatus.PASSWORD_EXPIRED.getCode() == status
                || UserStatus.ACTIVE.getCode() == status
                || UserStatus.PASSWORD_NEED_RESET.getCode() == status);
    }
}
