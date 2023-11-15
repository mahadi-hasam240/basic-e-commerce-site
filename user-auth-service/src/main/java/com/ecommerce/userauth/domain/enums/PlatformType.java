package com.ecommerce.userauth.domain.enums;

public enum PlatformType {
    MOBILE(1, "MOBILE"),
    WEB(2, "WEB");

    private int code;
    private String name;

    PlatformType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static boolean isPlatformTypeValid(int platformType) {
        for(PlatformType type : PlatformType.values()) {
            if(type.getCode() == platformType) return true;
        }
        return false;
    }

    public static boolean isPlatformTypeNotValid(int platformType) {
        return !isPlatformTypeValid(platformType);
    }

    public static boolean isMobile(int platformType) {
        return platformType == PlatformType.MOBILE.getCode();
    }
}
