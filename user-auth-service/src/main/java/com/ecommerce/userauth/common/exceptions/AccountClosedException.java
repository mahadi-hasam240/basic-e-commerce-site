package com.ecommerce.userauth.common.exceptions;

import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class AccountClosedException extends CustomAuthenticationException {
    public AccountClosedException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public AccountClosedException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
