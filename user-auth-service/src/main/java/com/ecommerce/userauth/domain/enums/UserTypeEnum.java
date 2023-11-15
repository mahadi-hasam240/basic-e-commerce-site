package com.ecommerce.userauth.domain.enums;

public enum UserTypeEnum {

    CUSTOMER(1, "Customer"),
    MERCHANT(2, "Merchant"),
    ;

    private int code;
    private String text;

    UserTypeEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static boolean isCustomer(int userTypeCode) {
        return UserTypeEnum.CUSTOMER.getCode() == userTypeCode;
    }

    public static boolean isMerchant(int userTypeCode) {
        return UserTypeEnum.MERCHANT.getCode() == userTypeCode;
    }

    public static boolean isUserTypeValid(int userType) {
        for (UserTypeEnum type : UserTypeEnum.values()) {
            if (type.getCode() == userType) return true;
        }
        return false;
    }

    public static boolean isUserTypeNotValid(int userType) {
        return !isUserTypeValid(userType);
    }
}
