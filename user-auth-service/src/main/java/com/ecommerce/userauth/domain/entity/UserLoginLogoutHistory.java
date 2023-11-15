package com.ecommerce.userauth.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "USER_LOGIN_LOGOUT_HISTORY")
public class UserLoginLogoutHistory extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_TOKEN")
    private String customerToken;

    @Column(name = "CUSTOMER_ACCOUNT_ID")
    private long customerAccountId;

    @Column(name = "ACTION_TYPE")
    private String actionType;

    @Column(name = "ACTION_DATE")
    private Date actionDate;

    @Column(name = "REQUEST_FROM")
    private int requestFrom;

    @Column(name = "REQUEST_IP")
    private String requestIP;

    @Column(name = "DEVICE_INFO")
    private String deviceInfo;
}
