package com.ecommerce.userauth.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Shahinur Beg
 * Created date : 1/18/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserTokenResponse implements Serializable {
    private String tokenSessionId;
    private int tokenStatus;
}
