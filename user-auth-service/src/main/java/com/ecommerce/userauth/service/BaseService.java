package com.ecommerce.userauth.service;

import com.ecommerce.userauth.common.exceptions.RecordNotFoundException;
import com.ecommerce.userauth.common.logger.CustomerAuthServiceLogger;
import com.ecommerce.userauth.common.utils.IPUtils;
import com.ecommerce.userauth.common.utils.SerializationUtils;
import com.ecommerce.userauth.domain.common.CurrentUserContext;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import java.util.Date;
import java.util.Optional;

public class BaseService {

    @Value("${jwt.token.prefix}")
    protected String tokenPrefix;

    @Value("${jwt.secret.key}")
    protected String jwtSecretKey;

    @Value("${jwt.token.expiry.minute}")
    protected String jwtExpiryTime;


    public ObjectMapper objectMapper;

    protected CustomerAuthServiceLogger logger;
    private LocaleMessageService messageService;

    protected HttpServletRequest request;

    public static final String CURRENT_USER_CONTEXT_HEADER = "CurrentContext";

    @Lazy
    @Autowired
    public void setMessageService(LocaleMessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    public void setLogger(CustomerAuthServiceLogger logger) {
        this.logger = logger;
    }
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getMessage(ResponseMessage key, Object... objects) {
        return messageService.getLocalMessage(key, objects);
    }

    public String getMessage(String key) {
        return messageService.getLocalMessage(key);
    }

    public Date getCurrentDate() {
        return new Date();
    }

    public Optional<String> getHeaderValue(String headerName) {

        try {
            return Optional.ofNullable(request.getHeader(headerName));
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage(), ex);
        }

        return Optional.empty();
    }

    public String getUserIdentity() {
        return getCurrentUserContext().getUserIdentity();
    }

    private String getCurrentUserContextHeaderValue() {
        Optional<String> userTokenOpt = getHeaderValue(CURRENT_USER_CONTEXT_HEADER);

        if (userTokenOpt.isEmpty())
            throw new RecordNotFoundException(ResponseMessage.RECORD_NOT_FOUND);

        return userTokenOpt.get();
    }

    public CurrentUserContext getCurrentUserContext() {
        String base64Data = getCurrentUserContextHeaderValue();
        String jsonObject = SerializationUtils.toByteArrayToString(base64Data);
        return toObject(jsonObject, CurrentUserContext.class);
    }

    public <T> T toObject(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    protected <T> String serializeObject(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    public String getRemoteIPAddress() {
        String realIp = IPUtils.getClientRealIpAddress(request);
        if (StringUtils.isNotBlank(realIp)) {
            return realIp;
        } else {
            return request.getRemoteAddr();
        }
    }

}
