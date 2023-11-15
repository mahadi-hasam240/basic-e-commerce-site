package com.ecommerce.userauth.common.exceptions;

import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class AccountLockedException extends CustomAuthenticationException {
    public AccountLockedException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public AccountLockedException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
