package com.ecommerce.userauth.domain.response.signup;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoreClientWithAccountResponse {
    private Long clientId;
    private Long savingsId;
}
