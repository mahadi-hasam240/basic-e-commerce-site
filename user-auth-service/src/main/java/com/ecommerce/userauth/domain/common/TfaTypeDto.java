package com.ecommerce.userauth.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TfaTypeDto implements Serializable {
    private Integer tfaType;
    private String tfaName;

    public TfaTypeDto(Integer tfaType) {
        this.tfaType = tfaType;
    }
}
