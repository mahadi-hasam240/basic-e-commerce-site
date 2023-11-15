package com.ecommerce.userauth.domain.request.signupRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Iabur on January 09, 2023.
 */
@Getter
@Setter
public class VerifyTokenRequest extends SignupBaseRequest{
    @NotNull
    private String sessionId;
    @NotNull
    private String passcode;
}
