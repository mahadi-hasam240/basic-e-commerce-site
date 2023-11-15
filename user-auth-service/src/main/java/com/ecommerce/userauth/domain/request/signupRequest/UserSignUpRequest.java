package com.ecommerce.userauth.domain.request.signupRequest;

import com.ecommerce.userauth.common.serializer.SensitiveDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class UserSignUpRequest extends SignupBaseRequest{
    @NotNull
    @JsonSerialize(using = SensitiveDataSerializer.class)
    private String password;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String phoneNo;

}
