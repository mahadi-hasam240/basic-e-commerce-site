package com.ecommerce.userauth.configuration;

import com.ecommerce.userauth.common.filters.JWTRequestFilter;
import com.ecommerce.userauth.common.handlers.AuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final JWTRequestFilter jwtRequestFilter;

    @Value("${password.strength}")
    private int passwordStrength;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(getAllowedUrls()).permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(authenticationFailureHandler);

//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.userDetailsService(userDetailsService).build();
    }

    private String[] getAllowedUrls() {
        return new String[]{
                "/",
                "/api/v1/forgot/password/**",
                "/api/v1/user-auth/**",
                "/api/v1/user-auth/biometric/authentication",
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-resources/**",
                "/webjars/**",
                "/api/v1/user-auth/signup/**",
        };
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(passwordStrength);
    }
}
