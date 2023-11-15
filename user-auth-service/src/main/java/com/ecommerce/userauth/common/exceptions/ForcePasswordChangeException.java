package com.ecommerce.userauth.common.exceptions;

import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class ForcePasswordChangeException extends CustomAuthenticationException {
    public ForcePasswordChangeException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public ForcePasswordChangeException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
