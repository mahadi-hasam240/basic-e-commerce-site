package com.ecommerce.userauth.service.password;


import com.ecommerce.userauth.api.BaseResource;
import com.ecommerce.userauth.common.annotations.SensitiveData;
import com.ecommerce.userauth.common.exceptions.InvalidRequestDataException;
import com.ecommerce.userauth.common.exceptions.PasswordExpiredException;
import com.ecommerce.userauth.domain.common.UserDto;
import com.ecommerce.userauth.domain.entity.User;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.ecommerce.userauth.domain.enums.StatusEnum;
import com.ecommerce.userauth.domain.request.ChangePasswordRequest;
import com.ecommerce.userauth.domain.request.ForgotPasswordRequest;
import com.ecommerce.userauth.repository.UserRepository;
import com.ecommerce.userauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Primary
public class CustomerPasswordService extends BaseResource implements ICustomerPasswordService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void requestForgotPassword(ForgotPasswordRequest request) {
        validateForgotPasswordRequest(request);
        updatePassword(request.getUserIdentity(), request.getNewPassword());
    }

    @Override
    public void requestChangePassword(ChangePasswordRequest request) {
        validateChangePasswordRequest(request);
        UserDto userDto = userService.getUserByUserName(request.getUserIdentity());

        String oldPassword = StringEscapeUtils.unescapeHtml(request.getOldPassword());
        String newPassword = StringEscapeUtils.unescapeHtml(request.getNewPassword());

        Boolean result = passwordEncoder.matches(oldPassword, userDto.getPassword());

        if (!result)
            throw new PasswordExpiredException(ResponseMessage.WRONG_PASSWORD);

        if (userDto.getUserIdentity().equals(oldPassword))
            throw new PasswordExpiredException(ResponseMessage.USER_ID_AS_PASSWORD);

        if (oldPassword.equals(newPassword))
            throw new PasswordExpiredException(ResponseMessage.OLD_PASSWORD_AS_NEW_PASSWORD);
        updatePassword(userDto.getUserIdentity(), newPassword);
    }

    private void validateChangePasswordRequest(ChangePasswordRequest request) {
        if (Objects.isNull(request)
                || StringUtils.isBlank(request.getNewPassword())
                || StringUtils.isBlank(request.getOldPassword()))
            throw new InvalidRequestDataException(ResponseMessage.INVALID_REQUEST_DATA);
    }

    private void validateForgotPasswordRequest(ForgotPasswordRequest request) {
        if (Objects.isNull(request)
                || StringUtils.isBlank(request.getNewPassword()))
            throw new InvalidRequestDataException(ResponseMessage.INVALID_REQUEST_DATA);
        if (!request.getNewPassword().equals(request.getNewPassword()))
            throw new InvalidRequestDataException(ResponseMessage.INVALID_REQUEST_DATA);

    }

    private void updatePassword(String userIdentity, @SensitiveData String newPassword) {
        User user = userRepository.getCustomerByUserIdentity(userIdentity, StatusEnum.ACTIVE.isStatus());
        if (Objects.isNull(user))
            throw new UsernameNotFoundException(getMessage(ResponseMessage.USER_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
