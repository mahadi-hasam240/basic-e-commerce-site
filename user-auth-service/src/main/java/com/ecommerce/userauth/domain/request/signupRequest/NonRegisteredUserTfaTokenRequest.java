package com.ecommerce.userauth.domain.request.signupRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonRegisteredUserTfaTokenRequest extends SignupBaseRequest {
    @NotNull
    private String featureCode;

    private String usedReferralCode;
}
