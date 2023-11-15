package com.ecommerce.userauth.service.password;

import com.ecommerce.userauth.domain.request.ChangePasswordRequest;
import com.ecommerce.userauth.domain.request.ForgotPasswordRequest;

public interface ICustomerPasswordService {

    void requestForgotPassword(ForgotPasswordRequest request);

    void requestChangePassword(ChangePasswordRequest request);
}
