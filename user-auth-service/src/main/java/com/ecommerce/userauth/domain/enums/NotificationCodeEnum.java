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
public enum NotificationCodeEnum {
    CUSTOMER_REGISTRATION_OTP_TEMPLATE(1, "CUSTOMER_REGISTRATION_OTP"),
    MERCHANT_REGISTRATION_OTP_TEMPLATE(4, "MERCHANT_REGISTRATION_OTP"),
    DEVICE_BINDING_OTP_TEMPLATE(5, "DEVICE BINDING OTP TEMPLATE"),
    SIGNUP_SUCCESS_TEMPLATE(6, "SIGNUP_SUCCESS"),
    ADMIN_CUSTOMER_SIGNUP_TEMPLATE(36, "CUSTOMER SIGN UP FROM ADMIN");


    private Integer code;
    private String type;

}
