package com.ecommerce.userauth.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "AUTH_USER")
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_IDENTITY")
    private String userIdentity;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ACCOUNT_STATUS")
    private Integer accountStatus;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "PHONE_NO")
    private String mobileNumber;


    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BAD_LI_ATTEMPT")
    private Integer badLoginAttempt = 0;

    @Column(name = "TEMP_BLOCK_DATE")
    private Date customerTempBlockDate;

    @Column(name = "TEMP_UNBLOCK_DATE")
    private Date customerTempUnblockDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
