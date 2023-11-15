package com.ecommerce.userauth.service.signup;

import com.ecommerce.userauth.common.exceptions.InvalidRequestDataException;
import com.ecommerce.userauth.common.utils.AppUtils;
import com.ecommerce.userauth.domain.entity.User;
import com.ecommerce.userauth.domain.enums.UserStatus;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.ecommerce.userauth.domain.event.NotificationValue;
import com.ecommerce.userauth.domain.event.TemplateValueEvent;
import com.ecommerce.userauth.domain.event.UserSignUpEvent;
import com.ecommerce.userauth.domain.request.signupRequest.UserSignUpRequest;
import com.ecommerce.userauth.repository.UserRepository;
import com.ecommerce.userauth.service.BaseService;
import com.ecommerce.userauth.service.UserService;
import com.ecommerce.userauth.service.NotificationService;
import com.ecommerce.userauth.service.NotificationTemplateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SignUpService extends BaseService {
    @Value("${otp.expiry.minutes}")
    private Integer otpExpireInMinutes;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final NotificationTemplateService notificationTemplateService;


    @Transactional
    public void getSignUpRequest(UserSignUpRequest request) {

        if (request.getPhoneNo() == null || request.getPassword() == null || request.getUsername() == null || request.getEmail() == null) {
            throw new InvalidRequestDataException(ResponseMessage.INVALID_REQUEST_DATA);
        }

        if (!userService.isValidPhoneNumber(request.getPhoneNo())) {
            throw new InvalidRequestDataException(ResponseMessage.INVALID_PHONE_NUMBER);
        }
        if (!userService.isContainPhoneNo(request)) {
            throw new InvalidRequestDataException(ResponseMessage.PIN_CONTAINS_PHONE_NUMBER);
        }

        User user = createUser(request);
        publishSignUpEvent(request);
        if (Objects.nonNull(user))
            sendSignUpNotificationValues(user);
    }

    private void sendSignUpNotificationValues(User user) {
        TemplateValueEvent event = new TemplateValueEvent();
        event.setUserIdentity(user.getUserIdentity());

        NotificationValue nv1 = new NotificationValue();
        nv1.getTemplateData().put("mobileNo", user.getMobileNumber());
        nv1.getTemplateData().put("walletNo", user.getMobileNumber());

        event.getNotificationValues().add(nv1);

        notificationTemplateService.publishNotificationTemplateValue(event);
    }



    private void publishSignUpEvent(UserSignUpRequest request) {

        UserSignUpEvent event = new UserSignUpEvent();
        event.setUserIdentity(request.getPhoneNo());
        notificationService.sendMsg(event);
    }

    private User createUser(UserSignUpRequest request) {
        if (!userRepository.existsCustomerByUserIdentity(request.getPhoneNo())) {
            User user = new User();
            user.setMobileNumber(request.getPhoneNo());
            user.setUserIdentity(request.getPhoneNo());
            user.setIsActive(true);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setCreatedDate(getCurrentDate());
            user.setCreatedBy(AppUtils.SERVICE_NAME);
            user.setAccountStatus(UserStatus.ACTIVE.getCode());
            return userRepository.save(user);
        }
        return null;
    }

}
