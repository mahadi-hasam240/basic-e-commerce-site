package com.ecommerce.userauth.domain.event;

import com.brainstation23.kafka.domain.ParentEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class NotificationEvent extends ParentEvent {
    static final String TYPE = "NOTIFICATION";
    private Integer notificationType;
    private List<String> toList;
    private String emailSubject;
    private Integer notificationCode;
    private String userIdentity;
    private Map<String, Object> templateData;

    @Override
    public String getType() {
        return TYPE;
    }
}
