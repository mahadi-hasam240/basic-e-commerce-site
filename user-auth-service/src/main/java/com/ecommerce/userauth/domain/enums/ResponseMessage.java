package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResponseMessage {

    OPERATION_SUCCESSFUL(ApiResponseCode.OPERATION_SUCCESSFUL.getResponseCode(), "operation.success"),
    RECORD_NOT_FOUND(ApiResponseCode.RECORD_NOT_FOUND.getResponseCode(), "record.not.found"),
    SERVICE_UNAVAILABLE(ApiResponseCode.SERVICE_UNAVAILABLE.getResponseCode(), "service.unavailable"),
    INCORRECT_CREDENTIALS(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "incorrect.credentials"),
    USER_NOT_FOUND(ApiResponseCode.RECORD_NOT_FOUND.getResponseCode(), "user.not.found"),
    INVALID_REQUEST_DATA(ApiResponseCode.INVALID_REQUEST_DATA.getResponseCode(), "invalid.request.data"),
    WRONG_PASSWORD(ApiResponseCode.WRONG_PASSWORD.getResponseCode(), "wrong.password"),
    USER_ID_AS_PASSWORD(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "user.id.as.password"),
    OLD_PASSWORD_AS_NEW_PASSWORD(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "old.password.as.new.password"),
    PASSWORD_MIN_MAX_LENGTH(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "password.min.max.length"),
    PASSWORD_NOTES(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "password.notes"),
    LAST_N_PASSWORD(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "last.n.passwords"),
    USER_NAME_ALREADY_USED(ApiResponseCode.ENTITY_ALREADY_EXIST.getResponseCode(), "username.already.used"),
    FORGOT_PASSWORD_NOT_ALLOWED(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "forgot.password.not.allowed"),
    USER_TEMPORARILY_LOCKED(ApiResponseCode.TEMPORARY_USER_LOCK.getResponseCode(), "account.temporary.locked"),
    LOGIN_FAILED(ApiResponseCode.AUTHENTICATION_FAILED.getResponseCode(), "login.failed.message"),
    AUTHENTICATION_FAILED(ApiResponseCode.AUTHENTICATION_FAILED.getResponseCode(), "authentication.failed"),
    PASSWORD_EXPIRED(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "password.expired"),
    FORCE_PASSWORD_CHANGE_EXCEPTION(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "force.password.expired.exception"),
    ACCOUNT_RESTRICTED(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "account.restricted"),
    ACCOUNT_CLOSED(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "account.closed"),
    INVALID_MOBILE_TOKEN(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "invalid.mobile.token"),
    INVALID_DEVICE_IDENTIFIER(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "invalid.device.identifier"),
    MOBILE_TOKEN_EXPIRED(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "mobile.token.expired"),
    APP_VERSION_NEED_TO_BE_UPDATED(ApiResponseCode.UPDATE_REQUIRED.getResponseCode(), "app.version.need.to.be.updated"),
    INTER_SERVICE_COMMUNICATION_ERROR(ApiResponseCode.INTER_SERVICE_COMMUNICATION_ERROR.getResponseCode(), "inter.service.communication.exception"),
    INTERNAL_SERVICE_EXCEPTION(ApiResponseCode.REQUEST_PROCESSING_FAILED.getResponseCode(), "internal.service.exception"),
    DATABASE_EXCEPTION(ApiResponseCode.DB_OPERATION_FAILED.getResponseCode(), "database.exception"),
    LOCALE_RECORD_NOT_FOUND(ApiResponseCode.RECORD_NOT_FOUND.getResponseCode(), "locale.record.not.found"),
    PHONE_NUMBER_ALREADY_USED(ApiResponseCode.ENTITY_ALREADY_EXIST.getResponseCode(), "phone.no.already.used"),
    INVALID_PHONE_NUMBER(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "invalid.phone.number"),
    DEVICE_BLACKLIST(ApiResponseCode.DEVICE_BLACKLIST.getResponseCode(), "device.blacklist"),
    OTP_NOT_VERIFIED(ApiResponseCode.OTP_NOT_VERIFIED.getResponseCode(), "otp.not.verified"),
    CBS_CLIENT_ID_ALREADY_USED(ApiResponseCode.ENTITY_ALREADY_EXIST.getResponseCode(), "cbs.client.id.already.used"),
    RECORD_ALREADY_EXIST(ApiResponseCode.INVALID_REQUEST_DATA.getResponseCode(), "record.already.exist"),
    INVALID_PIN_LENGTH(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "invalid.pin.length"),
    PIN_CONTAINS_PHONE_NUMBER(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "pin.contains.phone.number"),
    PIN_SEQUENCE_NOT_ALLOWED(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "pin.sequence.not.allowed"),
    CONSECUTIVE_NUMBER_NOT_ALLOWED(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "consecutive.number.not.allowed"),
    NOT_WALLET_USER(ApiResponseCode.RECORD_NOT_FOUND.getResponseCode(), "not.wallet.user"),
    INCORRECT_PIN_EXCEPTION(ApiResponseCode.VERIFICATION_FAILED.getResponseCode(), "incorrect.pin.exception"),
    JSON_PARSE_ERROR(ApiResponseCode.REQUEST_PROCESSING_FAILED.getResponseCode(), "json.parse.error"),
    PROFILE_UPDATE_OPERATION_SUCCESSFUL(ApiResponseCode.PROFILE_UPDATE_OPERATION_SUCCESSFUL.getResponseCode(), "profile.update.operation.success"),
    ENABLE_BIOMETRIC_LOGIN_OPERATION_SUCCESSFUL(ApiResponseCode.ENABLE_BIOMETRIC_LOGIN_OPERATION_SUCCESSFUL.getResponseCode(), "enable.biometric.login.operation.success"),
    DISABLE_BIOMETRIC_LOGIN_OPERATION_SUCCESSFUL(ApiResponseCode.DISABLE_BIOMETRIC_LOGIN_OPERATION_SUCCESSFUL.getResponseCode(), "disable.biometric.login.operation.success"),
    CHANGE_PIN_OPERATION_SUCCESSFUL(ApiResponseCode.CHANGE_PIN_OPERATION_SUCCESSFUL.getResponseCode(),"change.pin.operation.success"),
    LANGUAGE_CHANGE_OPERATION_SUCCESSFUL(ApiResponseCode.LANGUAGE_CHANGE_OPERATION_SUCCESSFUL.getResponseCode(),"language.change.operation.success"),
    REQUEST_MONEY_OPERATION_SUCCESSFUL(ApiResponseCode.REQUEST_MONEY_OPERATION_SUCCESSFUL.getResponseCode(),"request.money.operation.success"),
    CANCEL_REQUEST_MONEY_OPERATION_SUCCESSFUL(ApiResponseCode.CANCEL_REQUEST_MONEY_OPERATION_SUCCESSFUL.getResponseCode(),"cancel.request.money.operation.success"),
    ;
    private String responseCode;
    private String responseMessage;

}
