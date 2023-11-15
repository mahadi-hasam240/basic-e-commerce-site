package com.ecommerce.userauth.common.exceptions;

import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class AccountRestrictedException extends CustomAuthenticationException {
    public AccountRestrictedException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public AccountRestrictedException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
