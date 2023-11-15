package com.ecommerce.userauth.common.exceptions;

import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class RecordExistException extends CustomRootException {
    public RecordExistException(ResponseMessage responseMessage) {
        super(responseMessage);
    }

    public RecordExistException(String messageCode, String messageKey) {
        super(messageCode, messageKey);
    }
}
