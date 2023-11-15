package com.ecommerce.userauth.domain.request.signupRequest;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    @NotNull
    private String mobileNumber;
    @NotNull
    private String accountHolderFirstName;
    @NotNull
    private String accountHolderLastName;
    private String accountHolderDOB;

    public static ClientRequest from(UserSignUpRequest request) {
        return ClientRequest.builder()
                .accountHolderFirstName("client-user")
                .accountHolderLastName("" + request.getPhoneNo())
                .mobileNumber(request.getPhoneNo())
                .accountHolderDOB("2000-01-01")
                .build();
    }
    public static ClientRequest convert(SignupBaseRequest request) {
        return ClientRequest.builder()
                .accountHolderFirstName("client-user")
                .accountHolderLastName("" + request.getPhoneNo())
                .mobileNumber(request.getPhoneNo())
                .accountHolderDOB("2000-01-01")
                .build();
    }
}
