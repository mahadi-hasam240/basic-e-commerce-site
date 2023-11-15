package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LangLocale {
    ENGLISH("en"),
    US("en_US"),
    BANGLA("bn");

    private final String value;

    public static LangLocale fromValue(String value) {
        for (LangLocale locale : LangLocale.values()) {
            if (locale.value.equalsIgnoreCase(value)) {
                return locale;
            }
        }
        return null;
    }
}
