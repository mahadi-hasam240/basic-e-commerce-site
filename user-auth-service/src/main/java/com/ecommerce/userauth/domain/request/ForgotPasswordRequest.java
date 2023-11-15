package com.ecommerce.userauth.domain.request;

import com.ecommerce.userauth.common.serializer.SensitiveDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class ForgotPasswordRequest implements Serializable {

    private  String sessionId;
    private String userIdentity;
    @JsonSerialize(using = SensitiveDataSerializer.class)
    private String newPassword;
    @JsonSerialize(using = SensitiveDataSerializer.class)
    private String confirmPassword;

}
