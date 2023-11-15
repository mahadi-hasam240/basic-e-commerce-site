package com.ecommerce.userauth.domain.event;

import com.brainstation23.kafka.domain.ParentEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ActivityLogEvent extends ParentEvent {
    static final String TYPE = "TEMPLATE_VALUE";

    private String userIdentity;
    private String activityTypeName;
    private String activityTypeCode;
    private String activityStatus;
    private String activityStatusCode;
    private Date activityDate;
    private String device;
    @Override
    public String getType() {
        return TYPE;
    }
}
