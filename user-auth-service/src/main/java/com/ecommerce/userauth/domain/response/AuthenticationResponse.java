package com.ecommerce.userauth.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@Jacksonized
@Builder
public class AuthenticationResponse implements Serializable {
    private String token;
    private String userIdentity;
    private String userFUllName;
    private String lastLoginDate;
    private String passwordExpiryDate;
    private int accountStatus;
    private String email;
    private String sessionKey;
}
