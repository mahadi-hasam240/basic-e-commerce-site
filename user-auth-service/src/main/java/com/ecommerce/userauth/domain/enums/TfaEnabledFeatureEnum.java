package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author Shahinur Beg
 * Created date : 1/8/2023
 */
@AllArgsConstructor
@Getter
public enum TfaEnabledFeatureEnum {
    DEVICE_REGISTRATION(101, "Change Password"),
    FORGOT_PASSWORD(102, "Forgot Password"),
    SIGN_UP(103, "User Registration"),
    DEVICE_BINDING(110, "Device Binding"),
    ;

    private final Integer code;
    private final String text;

    public static String getFeatureName(Integer featureCode) {
        if (Objects.isNull(featureCode)) return StringUtils.EMPTY;

        for (TfaEnabledFeatureEnum item : TfaEnabledFeatureEnum.values()) {
            if (item.code.equals(featureCode))
                return item.getText();
        }

        return StringUtils.EMPTY;
    }
}
