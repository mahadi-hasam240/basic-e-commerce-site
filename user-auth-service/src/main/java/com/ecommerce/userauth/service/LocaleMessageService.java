package com.ecommerce.userauth.service;

import com.ecommerce.userauth.common.utils.BNLocalMessageReader;
import com.ecommerce.userauth.domain.enums.LangLocale;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleMessageService {

    private final HttpServletRequest request;
    private final MessageSource messageSource;

    public LocaleMessageService(HttpServletRequest request,
                                MessageSource messageSource) {
        this.request = request;
        this.messageSource = messageSource;
    }

    public Locale getLocale(){
        return request.getLocale();
    }

    public String getLocalMessage(ResponseMessage key, Object... objects) {
        return messageSource.getMessage(key.getResponseMessage(), objects, getLocale());
    }

    public String getLocalMessage(ResponseMessage key) {
        return this.getLocalMessage(key,  getLocale());
    }

    public String getLocalMessage(ResponseMessage key, Locale locale) {
        if (LangLocale.BANGLA.getValue().equalsIgnoreCase(locale.getLanguage())) {
            return BNLocalMessageReader.getMessage(key.getResponseMessage());
        }
        return messageSource.getMessage(key.getResponseMessage(), null, locale);
    }

    public String getLocalMessage(String key) {
        return messageSource.getMessage(key, null, getLocale());
    }

}
