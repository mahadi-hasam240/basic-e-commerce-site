package com.ecommerce.userauth.common.handlers;

import com.ecommerce.userauth.common.exceptions.CustomRootException;
import com.ecommerce.userauth.common.exceptions.PreValidationException;
import com.ecommerce.userauth.common.logger.CustomerAuthServiceLogger;
import com.ecommerce.userauth.domain.common.ApiResponse;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.ecommerce.userauth.service.LocaleMessageService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomerAuthServiceExceptionHandler extends BaseExceptionHandler {

    private final LocaleMessageService localeMessageService;
    private final CustomerAuthServiceLogger logger;

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<ApiResponse<Void>> handleFeignException(FeignException ex) {
        logger.error(ex.getLocalizedMessage(), ex);
        String message = processFeignExceptionMessage(ex.status(), ex.contentUTF8());
        ApiResponse<Void> apiResponse = buildApiResponse(ResponseMessage.INTER_SERVICE_COMMUNICATION_ERROR.getResponseCode(), message);
        HttpStatus httpStatus = ex.status() == HttpStatus.BAD_REQUEST.value() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    @ExceptionHandler(PreValidationException.class)
    public final ResponseEntity<ApiResponse<Void>> handlePreValidationException(PreValidationException ex) {
        errorLogger.error(ex.getLocalizedMessage(), ex);
        ApiResponse<Void> apiResponse = buildApiResponse(ex.getMessageCode(), getMessage(ex.getMessage()));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<ApiResponse<Void>> handleDBException(DataAccessException ex) {
        errorLogger.error(ex.getLocalizedMessage(), ex);
        String rootCause = Objects.nonNull(ex.getRootCause()) ? ex.getRootCause().toString() : "";
        errorLogger.error("Root Cause: " + rootCause);
        ApiResponse<Void> apiResponse = buildApiResponse(ResponseMessage.DATABASE_EXCEPTION.getResponseCode(), getMessage(ResponseMessage.DATABASE_EXCEPTION.getResponseMessage()));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(CustomRootException.class)
    public final ResponseEntity<ApiResponse<Void>> handleCustomException(CustomRootException ex) {
        errorLogger.error(ex.getLocalizedMessage(), ex);
        ApiResponse<Void> apiResponse = buildApiResponse(ex.getMessageCode(), getMessage(ex.getMessage()));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponse<Void>> commonException(Exception ex) {
        errorLogger.error(ex.getLocalizedMessage(), ex);
        ApiResponse<Void> apiResponse = buildApiResponse(ResponseMessage.INTERNAL_SERVICE_EXCEPTION.getResponseCode(), getMessage(ResponseMessage.INTERNAL_SERVICE_EXCEPTION.getResponseMessage()));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> collect = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (oldValue, newValue) -> newValue));

        ApiResponse<Object> apiResponse = buildApiResponse(ResponseMessage.INVALID_REQUEST_DATA.getResponseCode(), getMessage(ResponseMessage.INVALID_REQUEST_DATA.getResponseMessage()), collect);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    private String getMessage(String messageKey) {
        String message = StringUtils.EMPTY;

        try {
            message = localeMessageService.getLocalMessage(messageKey);
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage(), ex);
        }

        return StringUtils.isNotBlank(message) ? message : messageKey;
    }

}
