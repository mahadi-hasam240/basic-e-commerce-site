package com.ecommerce.userauth.domain.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Attachment implements Serializable {
    private String attachmentFileName;
    private String attachmentFileContentType;
    private byte[] attachmentFileContent;
}
