package com.ecommerce.userauth.domain.enums;

import java.util.EnumSet;

public enum StatusEnum {
    ACTIVE(true, "Active"),
    INACTIVE(false, "Inactive"),
    TRUE(true, "true"),
    FALSE(false,"false");

    private boolean status;
    private String text;

    StatusEnum(boolean status, String text) {
        this.status = status;
        this.text = text;
    }

    public boolean isStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public static boolean getStatusByText(String text){
        return EnumSet.allOf(StatusEnum.class)
                .stream()
                .filter(item -> item.getText().equalsIgnoreCase(text))
                .findFirst()
                .map(StatusEnum::isStatus)
                .get();
    }
}
