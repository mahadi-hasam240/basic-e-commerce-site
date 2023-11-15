package com.ecommerce.userauth.domain.common;
import com.ecommerce.userauth.common.serializer.SensitiveDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private String userIdentity;
    private  String email;
    private  String phoneNumber;

    @JsonSerialize(using = SensitiveDataSerializer.class)
    private String password;
    private Date customerTempBlockDateTime;
    private Date customerTempUnblockDateTime;
    private Integer customerStatus;
    private List<String> authorities;

    public UserDto(String userIdentity) {
        this.userIdentity = userIdentity;
    }
}
