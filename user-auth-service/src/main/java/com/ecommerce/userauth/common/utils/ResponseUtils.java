package com.ecommerce.userauth.common.utils;

import com.ecommerce.userauth.domain.common.ApiResponse;
import com.ecommerce.userauth.domain.enums.ResponseMessage;

public class ResponseUtils {

    public static <T> ApiResponse<T> createResponseObject(String message) {
        ApiResponse apiResponse = new ApiResponse<T>();
        apiResponse.setResponseCode(ResponseMessage.OPERATION_SUCCESSFUL.getResponseCode());
        apiResponse.setResponseMessage(message);
        return apiResponse;
    }

    public static <T> ApiResponse<T> createResponseObject(String message, T data) {
        ApiResponse apiResponse = new ApiResponse<T>();
        apiResponse.setResponseCode(ResponseMessage.OPERATION_SUCCESSFUL.getResponseCode());
        apiResponse.setResponseMessage(message);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> createResponseObject(ResponseMessage responseMessage, T data) {
        ApiResponse apiResponse = new ApiResponse<T>();
        apiResponse.setResponseCode(responseMessage.getResponseCode());
        apiResponse.setResponseMessage(responseMessage.getResponseMessage());
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> createResponseObject(ResponseMessage responseMessage) {
        ApiResponse apiResponse = new ApiResponse<T>();
        apiResponse.setResponseCode(responseMessage.getResponseCode());
        apiResponse.setResponseMessage(responseMessage.getResponseMessage());
        return apiResponse;
    }

}
