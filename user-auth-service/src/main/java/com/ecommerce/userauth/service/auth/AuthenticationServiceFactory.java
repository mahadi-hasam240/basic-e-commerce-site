package com.ecommerce.userauth.service.auth;

import com.ecommerce.userauth.common.annotations.NoLogging;
import com.ecommerce.userauth.common.utils.ApplicationContextHolder;
import org.springframework.stereotype.Component;


@NoLogging
@Component
public class AuthenticationServiceFactory {

    public AuthenticationService getBasicAuthService() {
        return ApplicationContextHolder.getContext().getBean(BasicAuthServiceImpl.class);
    }

}
