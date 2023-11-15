package com.ecommerce.userauth.service.auth;

import com.ecommerce.userauth.common.exceptions.InvalidTokenException;
import com.ecommerce.userauth.common.utils.JWTUtils;
import com.ecommerce.userauth.domain.enums.UserTypeEnum;
import com.ecommerce.userauth.domain.request.AuthenticationRequest;
import com.ecommerce.userauth.domain.response.AuthenticationResponse;
import com.ecommerce.userauth.service.BaseService;
import com.ecommerce.userauth.service.IUserService;
import com.ecommerce.userauth.service.UserServiceFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BasicAuthServiceImpl extends BaseService implements AuthenticationService {

    private final UserDetailsService userDetailService;
    private final UserServiceFactory userServiceFactory;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        String userName = request.getUserIdentity();
        String password = request.getPassword();


        IUserService iUserService = userServiceFactory.getUserDetailsByType(1);
        return iUserService.authenticateUser(request);

    }

    @Override
    public Boolean logout(){
        userServiceFactory.getUserDetailsByType(UserTypeEnum.CUSTOMER.getCode()).logout();

        return Boolean.TRUE;
    }

    @Override
    public UserDetails validateToken(String bearerToken) {
        if (StringUtils.isBlank(bearerToken) || !StringUtils.startsWith(bearerToken, tokenPrefix))
            throw new InvalidTokenException();

        String jwtToken = JWTUtils.trimToken(bearerToken, tokenPrefix);
        String userName = JWTUtils.extractUserName(jwtToken, jwtSecretKey);

        UserDetails userDetails = userDetailService.loadUserByUsername(userName);
        if (!JWTUtils.validateToken(jwtToken, userDetails.getUsername(), jwtSecretKey))
            throw new InvalidTokenException();

        return userDetails;
    }
}
