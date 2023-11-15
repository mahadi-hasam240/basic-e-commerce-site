package com.ecommerce.userauth.common.exceptions;

import com.ecommerce.userauth.domain.enums.ResponseMessage;

public abstract class CustomRootException extends RuntimeException {
    private String messageCode;

    public CustomRootException(ResponseMessage responseMessage) {
        super(responseMessage.getResponseMessage());
        this.messageCode = responseMessage.getResponseCode();
    }

    public CustomRootException(String messageCode, String messageKey) {
        super(messageKey);
        this.messageCode = messageCode;
    }

    public CustomRootException(String message) {
        super(message);
    }

    public String getMessageCode() {
        return messageCode;
    }

}
