package com.ecommerce.userauth.service;

import com.ecommerce.userauth.common.annotations.NoLogging;
import com.ecommerce.userauth.common.exceptions.ServiceUnavailableException;
import com.ecommerce.userauth.common.utils.ApplicationContextHolder;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.ecommerce.userauth.domain.enums.UserTypeEnum;
import org.springframework.stereotype.Component;

@NoLogging
@Component
public class UserServiceFactory {

    public IUserService getUserDetailsByType(int userType) {
        if (UserTypeEnum.isCustomer(userType)) {
            return ApplicationContextHolder.getContext().getBean(UserService.class);
        } else {
            throw new ServiceUnavailableException(ResponseMessage.SERVICE_UNAVAILABLE);
        }
    }

}
