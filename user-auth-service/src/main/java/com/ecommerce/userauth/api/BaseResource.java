package com.ecommerce.userauth.api;

import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.ecommerce.userauth.service.LocaleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public  class BaseResource {

    private LocaleMessageService localeMessageService;

    @Autowired
    public void setLocaleMessageService(LocaleMessageService localeMessageService) {
        this.localeMessageService = localeMessageService;
    }

    public String getMessage(ResponseMessage key) {
        return localeMessageService.getLocalMessage(key);
    }

}
