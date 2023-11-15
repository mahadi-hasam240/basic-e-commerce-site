package com.ecommerce.userauth.common.exceptions;

import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class PasswordExpiredException extends CustomAuthenticationException {
    public PasswordExpiredException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public PasswordExpiredException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
