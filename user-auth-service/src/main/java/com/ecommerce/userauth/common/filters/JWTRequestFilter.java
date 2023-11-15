package com.ecommerce.userauth.common.filters;

import com.ecommerce.userauth.common.logger.CustomerAuthServiceLogger;
import com.ecommerce.userauth.service.auth.AuthenticationServiceFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



@Component
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {

    private final AuthenticationServiceFactory authServiceFactory;
    private final CustomerAuthServiceLogger logger;

    @Value("${http.header.auth:Authorization}")
    private String authHeaderName;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authToken = httpServletRequest.getHeader(authHeaderName);

        UserDetails userDetails = null;

        try {
            userDetails = authServiceFactory
                    .getBasicAuthService()
                    .validateToken(authToken);
        } catch (Exception ex) {
            logger.error("OncePerRequestFilter: "+ex.getLocalizedMessage(), ex);
        }

        if (userDetails != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
