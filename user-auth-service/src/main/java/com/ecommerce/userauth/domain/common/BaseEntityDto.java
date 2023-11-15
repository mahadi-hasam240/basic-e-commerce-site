package com.ecommerce.userauth.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Jacksonized
@Builder
public class BaseEntityDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private String approvedBy;
    private Date createdDate;
    private Date updatedDate;
    private Date approvedDate;
    private Date lastUpdatedDate;
}
