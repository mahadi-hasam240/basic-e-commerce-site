package com.ecommerce.userauth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Shahinur Beg
 * Created date : 1/15/2023
 */
@AllArgsConstructor
@Getter
public enum LevelEnum {

    KYC_NOT_SUBMITTED(1, "BASIC"),
    KYC_SUBMITTED(2,"INTERMEDIATE"),
    KYC_APPROVED(3,"GRADUATE");

    private Integer roleCode;
    private String role;
}
