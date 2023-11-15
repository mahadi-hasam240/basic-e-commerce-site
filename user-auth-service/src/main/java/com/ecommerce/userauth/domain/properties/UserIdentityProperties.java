package com.ecommerce.userauth.domain.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "customer.identity")
@Getter
@Setter
public class UserIdentityProperties {
    private List<String> regexList;
}
