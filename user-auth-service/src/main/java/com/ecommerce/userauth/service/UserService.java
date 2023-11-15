package com.ecommerce.userauth.service;

import com.ecommerce.userauth.common.exceptions.*;
import com.ecommerce.userauth.common.utils.*;
import com.ecommerce.userauth.domain.common.BaseEntityDto;
import com.ecommerce.userauth.domain.common.UserDto;
import com.ecommerce.userauth.domain.common.UserJwtPayload;
import com.ecommerce.userauth.domain.entity.User;
import com.ecommerce.userauth.domain.entity.redis.SessionIdEntity;
import com.ecommerce.userauth.domain.entity.redis.TokenEntity;
import com.ecommerce.userauth.domain.enums.*;
import com.ecommerce.userauth.domain.event.ActivityLogEvent;
import com.ecommerce.userauth.domain.properties.UserIdentityProperties;
import com.ecommerce.userauth.domain.request.AuthenticationRequest;
import com.ecommerce.userauth.domain.request.signupRequest.UserSignUpRequest;
import com.ecommerce.userauth.domain.response.AuthenticationResponse;
import com.ecommerce.userauth.repository.UserRepository;
import com.ecommerce.userauth.service.redis.RedisSessionIdService;
import com.ecommerce.userauth.service.redis.RedisTokenService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerLoginLogoutHistoryService customerLoginLogoutHistoryService;
    private final RedisTokenService redisTokenService;
    private final UserIdentityProperties userIdentityProperties;
    private final ActivityLogService activityLogService;
    private final RedisSessionIdService redisSessionIdService;

    @Value("${encryption.secret.key}")
    private String secretKey;

    @Override
    public UserDto getUserByUserName(String userIdentity) {
        User user = userRepository.getCustomerByUserIdentity(userIdentity, StatusEnum.ACTIVE.isStatus());
        if (Objects.isNull(user))
            throw new RecordNotFoundException(ResponseMessage.USER_NOT_FOUND);

        return dtoToEntity(user);
    }

    @Override
    public boolean getExistsByIdentity(String userIdentity) {
        User response = userRepository.existsByIdentity(userIdentity);
        if (Objects.nonNull(response))
            throw new RecordExistException(ResponseMessage.USER_NAME_ALREADY_USED);
        return true;
    }

    private UserDto dtoToEntity(User user) {
        return UserDto.builder()
                .userIdentity(user.getUserIdentity())
                .password(user.getPassword())
                .customerStatus(user.getAccountStatus())
                .customerTempBlockDateTime(user.getCustomerTempBlockDate())
                .customerTempUnblockDateTime(user.getCustomerTempUnblockDate())
                .build();
    }

    @Transactional(noRollbackFor = {CustomAuthenticationException.class, LockedException.class})
    @Override
    public AuthenticationResponse authenticateBiometricUser(AuthenticationRequest request) {

        User customer = userRepository.getCustomerByUserIdentity(request.getUserIdentity(), StatusEnum.ACTIVE.isStatus());

        if (Objects.isNull(customer))
            throw new RecordNotFoundException(ResponseMessage.NOT_WALLET_USER);
        return authenticate(request, customer);
    }

    @Transactional(noRollbackFor = {CustomAuthenticationException.class, LockedException.class})
    @Override
    public AuthenticationResponse authenticateUser(AuthenticationRequest request) {

        validateUserIdentity(request);
        validateUserPassword(request);

        User user = userRepository.getByUserIdentity(request.getUserIdentity());

        if (Objects.isNull(user))
            throw new RecordNotFoundException(ResponseMessage.NOT_WALLET_USER);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            manageAuthenticationFailure(request.getUserIdentity());
            throw new CustomAuthenticationException(ResponseMessage.INCORRECT_PIN_EXCEPTION);
        }
        return authenticate(request, user);
    }


    private void validateUserIdentity(AuthenticationRequest request) {
        if (AppUtils.isUserIdentityFormatNotValid(userIdentityProperties.getRegexList(), request.getUserIdentity()))
            throw new InvalidRequestDataException(ResponseMessage.INVALID_REQUEST_DATA);
    }

    private void validateUserPassword(AuthenticationRequest request) {
        if (AppUtils.isUserIdentityFormatNotValid(userIdentityProperties.getRegexList(), request.getPassword()))
            throw new InvalidRequestDataException(ResponseMessage.INVALID_REQUEST_DATA);
    }


    private AuthenticationResponse authenticate(AuthenticationRequest request, User user) {
        if (UserStatus.isAccountAccessAble(user.getAccountStatus())) {
            return manageValidLogin(user, request);
        } else if (UserStatus.isCustomerTemporarilyBlocked(user.getAccountStatus())) {
            if (!(user.getCustomerTempBlockDate() != null
                    && user.getCustomerTempUnblockDate() != null))
                throw new CustomAuthenticationException(ResponseMessage.LOGIN_FAILED);

            if (isCustomerNotEligibleToUnblock(user.getCustomerTempUnblockDate()))
                throw new CustomAuthenticationException(ResponseMessage.USER_TEMPORARILY_LOCKED);

            user.setAccountStatus(UserStatus.ACTIVE.getCode());

            UserJwtPayload jwtPayload = prepareJwtPayload(user);
            String token = getCustomerToken(jwtPayload);
            saveTokenToRedis(user.getUserIdentity(), token);

            String sessionId = SessionIdUtils.getSessionId();
            saveSessionIdToRedis(token, sessionId);

            user.setBadLoginAttempt(0);
            user.setCustomerTempBlockDate(null);
            user.setCustomerTempUnblockDate(null);
            updateCustomer(user);
            publishActivityLogEvent(ActivityStatusEnum.SUCCESS,
                    ActivityTypeEnum.LOGIN,
                    request.getUserIdentity()
                 );

            return prepareTokenResponse(user, token, false, sessionId);
        } else if (UserStatus.isCustomerPasswordExpired(user.getAccountStatus())) {
            throw new PasswordExpiredException(ResponseMessage.PASSWORD_EXPIRED);
        } else if (UserStatus.isCustomerEligibleForForcePasswordChange(user.getAccountStatus())) {
            throw new ForcePasswordChangeException(ResponseMessage.FORCE_PASSWORD_CHANGE_EXCEPTION);
        } else if (UserStatus.isAccountRestricted(user.getAccountStatus())) {
            throw new AccountRestrictedException(ResponseMessage.ACCOUNT_RESTRICTED);
        } else if (UserStatus.isAccountClosed(user.getAccountStatus())) {
            throw new AccountClosedException(ResponseMessage.ACCOUNT_CLOSED);
        }
        throw new CustomAuthenticationException(ResponseMessage.AUTHENTICATION_FAILED);
    }

    private void saveSessionIdToRedis(String key, String sessionId) {
        SessionIdEntity sessionIdEntity = new SessionIdEntity();
        sessionIdEntity.setSessionRedisKey(key);
        sessionIdEntity.setSessionId(sessionId);

        redisSessionIdService.pushSessionId(sessionIdEntity);
    }

    private void saveTokenToRedis(String userName, String token) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUserName(userName.concat("_").concat(String.valueOf(UserTypeEnum.CUSTOMER.getCode())));
        tokenEntity.setToken(token);
        redisTokenService.pushToken(tokenEntity);
    }

    private AuthenticationResponse manageValidLogin(User user, AuthenticationRequest request) {

//        boolean isLoginPassword = customer.getAccountStatus().equals(CustomerStatus.PASSWORD_EXPIRED.getCode()) || isLoginPasswordExpired(customer);
        boolean isLoginPassword = false;
        UserJwtPayload jwtPayload = prepareJwtPayload(user);
        String token = getCustomerToken(jwtPayload);
        saveTokenToRedis(user.getUserIdentity(), token);

        String sessionId = SessionIdUtils.getSessionId();
        saveSessionIdToRedis(token, sessionId);

        if (user.getBadLoginAttempt() > 0)
            updateBadLICountToZero(user);

        createCustomerLoginLogoutHistory(token, user);


        publishActivityLogEvent(ActivityStatusEnum.SUCCESS,
                ActivityTypeEnum.LOGIN,
                request.getUserIdentity());

        return prepareTokenResponse(user, token, isLoginPassword, sessionId);
    }

    private AuthenticationResponse prepareTokenResponse(User user, String token, Boolean isLoginPasswordExpired, String sessionId) {

        String customerFullName = Objects.nonNull(user.getFirstName()) ? user
                .getFirstName()
                .concat(StringUtils.SPACE)
                .concat(Objects.nonNull(user.getLastName()) ? user.getLastName() : StringUtils.EMPTY) : StringUtils.EMPTY;

        return AuthenticationResponse.builder()
                .userIdentity(user.getUserIdentity())
                .userFUllName(customerFullName)
                .email(user.getEmail())
                .token(token)
                .sessionKey(sessionId)
                .build();
    }

    private UserJwtPayload prepareJwtPayload(User user) {
        return UserJwtPayload
                .builder()
                .userIdentity(CryptoUtils.encrypt(user.getUserIdentity(), secretKey))
                .userType(CryptoUtils.encrypt(String.valueOf(UserTypeEnum.CUSTOMER.getCode()), secretKey))
                .phoneNo(CryptoUtils.encrypt(user.getMobileNumber(), secretKey))
                .build();
    }

    private void updateBadLICountToZero(User user) {
        user.setBadLoginAttempt(0);
        updateCustomer(user);
    }

    private boolean isCustomerEligibleToUnblock(Date tempUnblockDate) {
        return tempUnblockDate.compareTo(new Date()) < 1;
    }

    private boolean isCustomerNotEligibleToUnblock(Date tempUnblockDate) {
        return !isCustomerEligibleToUnblock(tempUnblockDate);
    }

    private String getCustomerToken(UserJwtPayload payload) {
        Map customerData = objectMapper.convertValue(payload, Map.class);
        Map<String, Object> claims = new HashMap<>(customerData);
        return JWTUtils.generateToken(claims, payload.getUserIdentity(), jwtExpiryTime, jwtSecretKey);
    }

    public void manageAuthenticationFailure(String userName) {

        User user = userRepository.getCustomerByUserNameWithLock(userName, StatusEnum.ACTIVE.isStatus());

        if (UserStatus.isAccountAccessAble(user.getAccountStatus())) {
            if ((user.getBadLoginAttempt() + 1) == 100) {
                user.setAccountStatus(UserStatus.ACCOUNT_TEMPORARY_BLOCK.getCode());

                Date currentDate = new Date();
                user.setCustomerTempBlockDate(currentDate);
                user.setCustomerTempUnblockDate(DateTimeUtils.addMinutes(currentDate, 4));

                updateCustomer(user);

                /*AccountLockTemplateParams templateParams = prepareTemplateParams(customer);
                sendEmailSms(customer, countryCode, notificationSubject, UserFeatureEnum.ACCOUNT_LOCK, templateParams);*/

                throw new AccountLockedException(ResponseMessage.USER_TEMPORARILY_LOCKED);
            } else {
                user.setBadLoginAttempt(user.getBadLoginAttempt() + 1);
                updateCustomer(user);
            }
        } else if (UserStatus.isCustomerTemporarilyBlocked(user.getAccountStatus())) {
            /*AccountLockTemplateParams templateParams = prepareTemplateParams(customer);
            sendEmailSms(customer, countryCode, notificationSubject, UserFeatureEnum.ACCOUNT_LOCK, templateParams);*/
            throw new AccountLockedException(ResponseMessage.USER_TEMPORARILY_LOCKED);
        }
    }

    private void updateCustomer(User user) {
        userRepository.save(user);
    }

    private void createCustomerLoginLogoutHistory(String token,
                                                  User user
    ) {
        BaseEntityDto baseEntityDto = BaseEntityDto
                .builder()
                .createdBy(String.valueOf(user.getId()))
                .updatedBy(String.valueOf(user.getId()))
                .createdDate(new Date())
                .updatedDate(new Date())
                .lastUpdatedDate(new Date())
                .build();
        customerLoginLogoutHistoryService.userCustomerLoginLogoutHistory(token, user, baseEntityDto);
    }

    @Override
    public UserDto getActiveUserByUserId(Long userId) {
        UserDto userDto = userRepository.getCustomerById(userId);

        if (Objects.isNull(userDto))
            throw new RecordNotFoundException(ResponseMessage.USER_NOT_FOUND);

        return userDto;
    }

    public boolean isValidPhoneNumber(String phoneNo) {
        String regex = "^(?:\\+?88|0088)?01[3-9]\\d{8}$";
        return phoneNo.matches(regex);
    }

    public boolean isPhoneNumberExists(String phoneNo) {
        return userRepository.existsByPhoneNo(phoneNo);
    }

    public boolean isContainPhoneNo(UserSignUpRequest request) {
        String pin = request.getPassword();
        String account = request.getPhoneNo();
        for (int i = 0; i < account.length() - 3; i++) {
            String sequence = account.substring(i, i + 4);
            if (pin.contains(sequence)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSequenceNumber(String pin) {
        String regex = "^((?:0123|1234|2345|3456|4567|5678|6789)).*$";
        return !pin.matches(regex);
    }

    public boolean isConsecutive(String pin) {
        String regex = "(\\d)\\1{3}";
        return !pin.matches(regex);
    }

    @Override
    @Transactional
    public void logout() {

        String TokenKey = getCurrentUserContext().getUserIdentity()
                .concat(SpecialCharsEnum.UNDERSCORE.getSign()).concat(String.valueOf(UserTypeEnum.CUSTOMER.getCode()));

        String token = redisTokenService.getToken(TokenKey);
        if (StringUtils.isEmpty(token)) return;

        if (StringUtils.isEmpty(redisSessionIdService.getSession(token))) return;

        redisSessionIdService.deleteSession(token);
        redisTokenService.deleteToken(TokenKey);

        publishActivityLogEvent(ActivityStatusEnum.SUCCESS,
                ActivityTypeEnum.LOGOUT,
                getUserIdentity());

    }

    private void publishActivityLogEvent(ActivityStatusEnum activityStatusEnum,
                                         ActivityTypeEnum activityTypeEnum,
                                         String userIdentity
                                         ) {
        ActivityLogEvent activityLogEvent = ActivityLogEvent.builder()
                .activityStatusCode(activityStatusEnum.getCode())
                .activityStatus(activityStatusEnum.getName())
                .activityDate(getCurrentDate())
                .activityTypeCode(activityTypeEnum.getCode())
                .activityTypeName(activityTypeEnum.getName())
                .userIdentity(userIdentity)
                .build();
        activityLogService.publishEvent(activityLogEvent);
    }
}
