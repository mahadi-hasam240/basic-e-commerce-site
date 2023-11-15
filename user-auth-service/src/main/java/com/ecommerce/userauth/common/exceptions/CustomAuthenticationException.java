package com.ecommerce.userauth.common.exceptions;

import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class CustomAuthenticationException extends CustomRootException {
    public CustomAuthenticationException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public CustomAuthenticationException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
