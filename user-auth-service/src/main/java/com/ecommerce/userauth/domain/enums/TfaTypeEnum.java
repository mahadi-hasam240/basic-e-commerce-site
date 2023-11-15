package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Shahinur Beg
 * Created date : 1/8/2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TfaTypeEnum {
    OTP("10", "OTP"),
    PIN_VERIFICATION("11", "PIN Verification"),
    GOOGLE_AUTHENTICATOR("12", "Google Authenticator"),
    AUTHY("13", "Authy"),
    ;
    private String code;
    private String text;
}
