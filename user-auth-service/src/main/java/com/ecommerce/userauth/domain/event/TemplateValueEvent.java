package com.ecommerce.userauth.domain.event;

import com.brainstation23.kafka.domain.ParentEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TemplateValueEvent extends ParentEvent {
    static final String TYPE = "TEMPLATE_VALUE";

    private String userIdentity;
    private List<NotificationValue> notificationValues = new ArrayList<>();

    @Override
    public String getType() {
        return TYPE;
    }
}
