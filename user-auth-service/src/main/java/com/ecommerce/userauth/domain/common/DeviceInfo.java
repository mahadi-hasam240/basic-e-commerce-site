package com.ecommerce.userauth.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Builder
public class DeviceInfo implements Serializable {
    private int platformType;
    private String platformInfo;
    private String platformVersion;
    private String deviceIdentifier;
    private String appLanguage;
    private Double appVersionNo;
}