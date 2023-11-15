package com.ecommerce.userauth.service;

import com.ecommerce.userauth.domain.common.UserDto;
import com.ecommerce.userauth.domain.enums.SpecialCharsEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl extends BaseService implements UserDetailsService {

    private final UserServiceFactory userServiceFactory;

    @Override
    public UserDetails loadUserByUsername(String userNameWithType) {
        String userName = getUserName(userNameWithType);
        int userType = getUserType(userNameWithType);
        UserDto userDto = userServiceFactory.getUserDetailsByType(userType).getUserByUserName(userName);
        return prepareSecurityUser(userDto);
    }

    private User prepareSecurityUser(UserDto userDto) {
        List<SimpleGrantedAuthority> authorityList = prepareAuthorities(userDto.getAuthorities());
        return new User(userDto.getUserIdentity(), userDto.getPassword(), authorityList);
    }

    private List<SimpleGrantedAuthority> prepareAuthorities(List<String> authorities) {
        return Collections.emptyList();
    }

    private String getUserName(String userNameWithType) {
        String[] parts = splitUserName(userNameWithType);
        return parts[0];
    }

    private int getUserType(String userNameWithType) {
        String[] parts = splitUserName(userNameWithType);
        return Integer.parseInt(parts[1]);
    }

    private String[] splitUserName(String userNameWithType) {
        return userNameWithType.split(SpecialCharsEnum.COLON.getSign());
    }

}
