package com.ecommerce.userauth.common.handlers;

import com.ecommerce.userauth.common.logger.CustomerAuthServiceLogger;
import com.ecommerce.userauth.common.mappers.JacksonMapper;
import com.ecommerce.userauth.domain.common.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandler implements AuthenticationEntryPoint {

    private final CustomerAuthServiceLogger logger;
    private final JacksonMapper mapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        ServletServerHttpResponse res = new ServletServerHttpResponse(httpServletResponse);
        res.setStatusCode(HttpStatus.UNAUTHORIZED);
        logger.error(e.getLocalizedMessage(), e);
        res.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.getBody().write(
                mapper.writeValueAsString(buildApiResponse(e.getLocalizedMessage()))
                        .getBytes()
        );
    }

    private ApiResponse<Void> buildApiResponse(String message) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setResponseMessage(message);
        return apiResponse;
    }

}
