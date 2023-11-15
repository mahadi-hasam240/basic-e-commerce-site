package com.ecommerce.userauth.domain.request;
import com.ecommerce.userauth.common.serializer.SensitiveDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class ChangePasswordRequest  {
    private String userIdentity;
    @JsonSerialize(using = SensitiveDataSerializer.class)
    private String oldPassword;
    @JsonSerialize(using = SensitiveDataSerializer.class)
    private String newPassword;
    @JsonSerialize(using = SensitiveDataSerializer.class)
    private String confirmNewPassword;
}
