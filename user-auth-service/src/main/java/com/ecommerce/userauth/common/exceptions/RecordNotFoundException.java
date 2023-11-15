package com.ecommerce.userauth.common.exceptions;


import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class RecordNotFoundException extends CustomRootException {
    public RecordNotFoundException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public RecordNotFoundException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
