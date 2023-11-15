package com.ecommerce.userauth.service;


import com.brainstation23.kafka.producer.CommonProducer;
import com.brainstation23.kafka.utils.KafkaUtil;
import com.ecommerce.userauth.domain.event.TemplateValueEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationTemplateService {
    @Value("${bs.kafka.topic.template-value}")
    private String topic;
    private final CommonProducer commonProducer;

    public void publishNotificationTemplateValue(TemplateValueEvent event) {
        commonProducer.sendMessage(topic, KafkaUtil.prepareKafkaObject(UUID.randomUUID().toString(), Timestamp.from(Instant.now()), event));
    }
}
