package com.ecommerce.userauth.service;


import com.ecommerce.userauth.domain.common.BaseEntityDto;
import com.ecommerce.userauth.domain.entity.User;
import com.ecommerce.userauth.domain.entity.UserLoginLogoutHistory;
import com.ecommerce.userauth.domain.enums.LoginLogoutType;
import com.ecommerce.userauth.repository.UserLoginLogoutHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerLoginLogoutHistoryService extends BaseService {

    private final UserLoginLogoutHistoryRepository userLoginLogoutHistoryRepository;

    public CustomerLoginLogoutHistoryService(UserLoginLogoutHistoryRepository userLoginLogoutHistoryRepository) {
        this.userLoginLogoutHistoryRepository = userLoginLogoutHistoryRepository;
    }

    public void userCustomerLoginLogoutHistory(String token,
                                               User user,
                                               BaseEntityDto baseEntityDto) {
        UserLoginLogoutHistory historyObj = new UserLoginLogoutHistory();
        historyObj.setCustomerToken(token);
        historyObj.setCustomerAccountId(user.getId());
        historyObj.setActionDate(new Date());
        historyObj.setActionType(LoginLogoutType.LOGOUT.getCode());
        historyObj.setRequestIP(getRemoteIPAddress());
        historyObj.setCreatedBy(baseEntityDto.getCreatedBy());
        historyObj.setUpdatedBy(baseEntityDto.getUpdatedBy());
        historyObj.setCreatedDate(baseEntityDto.getCreatedDate());
        historyObj.setUpdatedDate(baseEntityDto.getUpdatedDate());

        userLoginLogoutHistoryRepository.save(historyObj);

    }
}
