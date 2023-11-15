package com.ecommerce.userauth.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Getter
@Jacksonized
@Builder(toBuilder = true)
public class UserJwtPayload implements Serializable {
    private String userIdentity;
    private String userType;
    private String userLevel;
    private String status;
    private String clientId;
    private String phoneNo;
    private String groupId;
}
