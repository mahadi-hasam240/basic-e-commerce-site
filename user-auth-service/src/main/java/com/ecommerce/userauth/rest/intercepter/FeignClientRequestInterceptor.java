package com.ecommerce.userauth.rest.intercepter;

import com.ecommerce.userauth.common.logger.CustomerAuthServiceLogger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
public class FeignClientRequestInterceptor implements RequestInterceptor {

    @Value("${http.header.current.context}")
    private String currentContext;

    @Value("${http.header.language}")
    private String languageHeader;

    private final CustomerAuthServiceLogger logger;
    private final HttpServletRequest request;

    public FeignClientRequestInterceptor(CustomerAuthServiceLogger logger,
                                         HttpServletRequest request) {
        this.logger = logger;
        this.request = request;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(currentContext, getCurrentContext());
        requestTemplate.header(languageHeader, getLanguageHeader());
    }

    private String getCurrentContext() {
        try {
            String token = request.getHeader(currentContext);
            if (StringUtils.isBlank(token)) return StringUtils.EMPTY;
            return token;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
        }
        return StringUtils.EMPTY;
    }

    private String getLanguageHeader() {
        try {
            String lHeader = request.getHeader(languageHeader);
            if (StringUtils.isBlank(lHeader)) return StringUtils.EMPTY;
            return lHeader;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
        }
        return Locale.ENGLISH.toString();
    }

}
