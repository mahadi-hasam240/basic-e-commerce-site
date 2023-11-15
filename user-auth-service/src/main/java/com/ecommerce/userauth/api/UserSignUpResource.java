package com.ecommerce.userauth.api;

import com.ecommerce.userauth.common.utils.AppUtils;
import com.ecommerce.userauth.common.utils.ResponseUtils;
import com.ecommerce.userauth.domain.common.ApiResponse;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.ecommerce.userauth.domain.request.signupRequest.NonRegisteredUserTfaTokenRequest;
import com.ecommerce.userauth.domain.request.signupRequest.UserSignUpRequest;
import com.ecommerce.userauth.domain.request.signupRequest.VerifyTokenRequest;
import com.ecommerce.userauth.domain.response.signup.NonRegisteredUserTfaTokenResponse;
import com.ecommerce.userauth.service.signup.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppUtils.BASE_URL)
public class UserSignUpResource extends BaseResource {

    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ApiResponse<Void> registerUser(@Validated @RequestBody UserSignUpRequest request) {
        signUpService.getSignUpRequest(request);
        return ResponseUtils.createResponseObject(ResponseMessage.OPERATION_SUCCESSFUL);
    }

}
