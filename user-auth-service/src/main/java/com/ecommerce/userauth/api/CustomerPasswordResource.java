package com.ecommerce.userauth.api;

import com.ecommerce.userauth.common.utils.AppUtils;
import com.ecommerce.userauth.common.utils.ResponseUtils;
import com.ecommerce.userauth.domain.common.ApiResponse;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.ecommerce.userauth.domain.request.ChangePasswordRequest;
import com.ecommerce.userauth.domain.request.ForgotPasswordRequest;
import com.ecommerce.userauth.service.password.ICustomerPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(AppUtils.BASE_URL)
public class CustomerPasswordResource extends BaseResource {

    private final ICustomerPasswordService customerPasswordService;

    @PostMapping("/forgot/password")
    public ApiResponse<Void> resetPassword(@RequestBody ForgotPasswordRequest request) {
        customerPasswordService.requestForgotPassword(request);
        return ResponseUtils.createResponseObject(getMessage(ResponseMessage.OPERATION_SUCCESSFUL));
    }

    @PostMapping("/change/password")
    public ApiResponse<Void> changePassword(@RequestBody ChangePasswordRequest request) {
        customerPasswordService.requestChangePassword(request);
        return ResponseUtils.createResponseObject(getMessage(ResponseMessage.OPERATION_SUCCESSFUL));
    }

}
