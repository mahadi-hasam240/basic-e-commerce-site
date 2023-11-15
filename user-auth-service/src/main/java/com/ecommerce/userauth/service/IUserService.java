package com.ecommerce.userauth.service;


import com.ecommerce.userauth.domain.common.UserDto;
import com.ecommerce.userauth.domain.request.AuthenticationRequest;
import com.ecommerce.userauth.domain.response.AuthenticationResponse;

public interface IUserService {
    UserDto getUserByUserName(String userName);
    AuthenticationResponse authenticateUser(AuthenticationRequest request);
    AuthenticationResponse authenticateBiometricUser(AuthenticationRequest request);
    UserDto getActiveUserByUserId(Long userId);
    boolean getExistsByIdentity(String userIdentity);

    void logout();
}
