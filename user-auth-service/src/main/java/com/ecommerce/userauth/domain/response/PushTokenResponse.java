package com.ecommerce.userauth.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Shahinur Beg
 * Created date : 4/30/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushTokenResponse implements Serializable {
    private String pushToken;
}
