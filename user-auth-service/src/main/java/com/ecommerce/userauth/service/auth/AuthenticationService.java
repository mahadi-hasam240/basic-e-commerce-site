package com.ecommerce.userauth.service.auth;
import com.ecommerce.userauth.domain.request.AuthenticationRequest;
import com.ecommerce.userauth.domain.response.AuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetails;


public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    UserDetails validateToken(String token);

    Boolean logout();
}
