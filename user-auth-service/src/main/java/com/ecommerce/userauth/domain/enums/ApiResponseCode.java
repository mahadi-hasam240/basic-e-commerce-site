package com.ecommerce.userauth.domain.enums;

import com.ecommerce.userauth.domain.common.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ApiResponseCode {

    OPERATION_SUCCESSFUL("S100000"),
    PROFILE_UPDATE_OPERATION_SUCCESSFUL("S100101"),
    ENABLE_BIOMETRIC_LOGIN_OPERATION_SUCCESSFUL("S100102"),
    DISABLE_BIOMETRIC_LOGIN_OPERATION_SUCCESSFUL("S100103"),
    CHANGE_PIN_OPERATION_SUCCESSFUL("S100104"),
    LANGUAGE_CHANGE_OPERATION_SUCCESSFUL("S100105"),
    REQUEST_MONEY_OPERATION_SUCCESSFUL("S100106"),
    CANCEL_REQUEST_MONEY_OPERATION_SUCCESSFUL("S100107"),
    RECORD_NOT_FOUND("E000101"),
    INVALID_REQUEST_DATA("E000102"),
    SERVICE_UNAVAILABLE("E000103"),
    UNIQUE_CONSTRAINT_VIOLATION("E000104"),
    VERIFICATION_FAILED("E000105"),
    REQUEST_PROCESSING_FAILED("E000106"),
    INVALID_TRANSACTION_PIN("E000107"),
    OTP_GENERATION_FAILED("E000108"),
    INACTIVE_ENTITY("E000109"),
    TFA_NOT_SUPPORTED("E000110"),
    INTER_SERVICE_COMMUNICATION_ERROR("E000111"),
    DB_OPERATION_FAILED("E000010"),
    BAD_OTP_ATTEMPTS_EXCEEDED("E000112"),
    WRONG_PASSWORD("E000113"),
    ENTITY_ALREADY_EXIST("E000114"),
    TEMPORARY_USER_LOCK("E000115"),
    AUTHENTICATION_FAILED("E000116"),
    UPDATE_REQUIRED("E000117"),
    DEVICE_BLACKLIST("E000160"),
    OTP_NOT_VERIFIED("E000161"),
    ;

    private String responseCode;

    public static boolean isOperationSuccessful(ApiResponse apiResponse) {
        return Objects.nonNull(apiResponse) && apiResponse.getResponseCode().equals(ApiResponseCode.OPERATION_SUCCESSFUL.getResponseCode());
    }

    public static boolean isNotOperationSuccessful(ApiResponse apiResponse) {
        return !isOperationSuccessful(apiResponse);
    }

}
