package com.ecommerce.userauth.domain.request;
import com.ecommerce.userauth.common.serializer.SensitiveDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationRequest implements Serializable {

    @NotEmpty
    private String userIdentity;

    @NotEmpty
    @JsonSerialize(using = SensitiveDataSerializer.class)
    private String password;


}
