package com.ecommerce.userauth.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Shahinur Beg
 * Created date : 1/23/2023
 */
@Data
public class CurrentUserContext implements Serializable {
    private String userIdentity;
    private String userType;
    private Integer userLevel;
    private Integer userStatus;
    private String userPhoneNo;
    private String userCbsClientId;
}
