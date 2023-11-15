package com.ecommerce.userauth.common.exceptions;


import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class InvalidTokenException extends CustomAuthenticationException {
    public InvalidTokenException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public InvalidTokenException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }

    public InvalidTokenException() {
        super("", "");
    }
}
