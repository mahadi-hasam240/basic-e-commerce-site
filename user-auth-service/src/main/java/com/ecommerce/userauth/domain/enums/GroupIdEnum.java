package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum GroupIdEnum {
    CUSTOMER_EKYC_COMPLETE_GROUP("EKYC_COMPLETE_GROUP", "SPCL101"),
    MERCHANT_EKYC_COMPLETE_GROUP("MERCHANT_EKYC_COMPLETE_GROUP", "MSPCL101"),
    CUSTOMER_EKYC_NON_COMPLETE_GROUP("CUSTOMER_EKYC_NON_COMPLETE_GROUP","KYCL12"),
    MERCHANT_EKYC_NON_COMPLETE_GROUP("MERCHANT_EKYC_NON_COMPLETE_GROUP","MKYC12"),
    PUBLIC_GROUP("PUBLIC_GROUP","PBL101"),
    DEFAULT_GROUP_AFTER_SIGNUP("DEFAULT_GROUP_AFTER_SIGNUP","Default"),
    ;
    private static Map<String, String> valueToTextMapping;
    private final String key;
    private final String value;

    public static String getValueByKey(String key){
        if(valueToTextMapping == null){
            initMapping();
        }
        return valueToTextMapping.get(key);
    }

    public static Map<String, String> initMapping(){
        valueToTextMapping = new HashMap<>();
        for(GroupIdEnum s : values()){
            valueToTextMapping.put(s.key, s.value);
        }
        return valueToTextMapping;
    }
}
