package com.ecommerce.userauth.service;


import com.brainstation23.kafka.domain.EventWrapper;
import com.brainstation23.kafka.producer.CommonProducer;
import com.brainstation23.kafka.utils.KafkaUtil;
import com.ecommerce.userauth.domain.event.ActivityLogEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ActivityLogService {
    @Value("${bs.kafka.topic.user-activity-log}")
    private String topic;
    private final CommonProducer commonProducer;

    public void publishEvent(ActivityLogEvent activityLogEvent) {
        EventWrapper<Object> eventObject = KafkaUtil.prepareKafkaObject(String.valueOf(UUID.randomUUID()), Timestamp.from(Instant.now()), activityLogEvent);
        commonProducer.sendMessage(topic, eventObject);
    }
}
