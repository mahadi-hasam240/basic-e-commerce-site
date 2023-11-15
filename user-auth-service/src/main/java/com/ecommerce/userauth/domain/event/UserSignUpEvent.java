package com.ecommerce.userauth.domain.event;

import com.brainstation23.kafka.domain.ParentEvent;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserSignUpEvent extends ParentEvent {

    static final String TYPE = "CUSTOMER_SIGN_UP";
    private String userIdentity;
    private String referralCode;

    @Override
    public String getType() {
        return TYPE;
    }
}
