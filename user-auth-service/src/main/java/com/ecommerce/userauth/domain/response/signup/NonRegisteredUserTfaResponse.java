package com.ecommerce.userauth.domain.response.signup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Iabur on January 09, 2023.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NonRegisteredUserTfaResponse implements Serializable {
    private String tokenSessionId;
}
