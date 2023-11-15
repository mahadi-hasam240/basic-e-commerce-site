package com.ecommerce.userauth.common.exceptions;


import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class InvalidRequestDataException extends PreValidationException {
    public InvalidRequestDataException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public InvalidRequestDataException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
