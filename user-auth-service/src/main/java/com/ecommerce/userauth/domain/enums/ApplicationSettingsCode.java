package com.ecommerce.userauth.domain.enums;

public enum ApplicationSettingsCode {

    PASSWORD_EXPIRES_IN_DAYS("1003", "Password Expires In Days"),
    BAD_PASSWORD_LIMIT("1004", "Bad Password Limit"),
    BAD_OTP_LIMIT("1005", "Bad OTP Limit"),
    OTP_EXPIRES_IN_MINUTE("1006", "OTP Expires In Minute"),
    LOCK_OTP_IN_MINUTES_FOR_NON_REGISTERED_USER("1007", "Lock OTP In Minutes For Non Registered User"),
    OTP_LENGTH("1008", "OTP Length"),
    TRANSACTION_PIN_REGEX("1009", "Transaction PIN Regex"),
    DAILY_OTP_ATTEMPT_LIMIT_FOR_NON_REGISTERED_USER("1010", "Daily OTP Attempt Limit For Non Registered User"),
    USER_LAST_PASSWORD_NOT_ALLOWED_COUNT("1011", "User Last Password Not Allowed Count"),
    USER_TEMPORARY_BLOCK_IN_MINUTE("1012", "User Temporary Block In Minutes"),
    CURRENT_APP_VERSION("1018", "Current app version."),
    ;

    private String code;
    private String name;

    ApplicationSettingsCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
