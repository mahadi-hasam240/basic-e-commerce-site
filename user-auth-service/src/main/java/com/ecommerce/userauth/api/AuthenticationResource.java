package com.ecommerce.userauth.api;

import com.ecommerce.userauth.common.utils.AppUtils;
import com.ecommerce.userauth.common.utils.ResponseUtils;
import com.ecommerce.userauth.domain.common.ApiResponse;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.ecommerce.userauth.domain.request.AuthenticationRequest;
import com.ecommerce.userauth.domain.response.AuthenticationResponse;
import com.ecommerce.userauth.service.auth.AuthenticationServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(AppUtils.BASE_URL)
public class AuthenticationResource extends BaseResource {

    private final AuthenticationServiceFactory authServiceFactory;

    @PostMapping("/user/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse authenticationResponse = authServiceFactory.getBasicAuthService().authenticate(request);
        return ResponseUtils.createResponseObject(ResponseMessage.OPERATION_SUCCESSFUL);
    }

    @GetMapping("/user/logout")
    public ApiResponse<Boolean> logout(){
        return ResponseUtils.createResponseObject(ResponseMessage.OPERATION_SUCCESSFUL,
                authServiceFactory.getBasicAuthService().logout());
    }
   

}
