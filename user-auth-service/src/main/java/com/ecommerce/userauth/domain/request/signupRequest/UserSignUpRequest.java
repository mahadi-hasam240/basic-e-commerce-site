package com.ecommerce.userauth.domain.request.signupRequest;

import com.ecommerce.userauth.common.serializer.SensitiveDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Getter
@Setter
public class UserSignUpRequest implements Serializable {
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
