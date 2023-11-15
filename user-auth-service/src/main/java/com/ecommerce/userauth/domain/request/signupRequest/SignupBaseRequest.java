package com.ecommerce.userauth.domain.request.signupRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Iabur on January 09, 2023.
 */
@Getter
@Setter
public class SignupBaseRequest implements Serializable {
    @NotNull
    public String phoneNo;
}
