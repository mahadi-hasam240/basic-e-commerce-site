package com.ecommerce.userauth.common.exceptions;

import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class ServiceUnavailableException extends CustomRootException {
    public ServiceUnavailableException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public ServiceUnavailableException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
