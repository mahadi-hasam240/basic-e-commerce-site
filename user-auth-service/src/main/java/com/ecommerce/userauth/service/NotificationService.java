package com.ecommerce.userauth.service;

import com.brainstation23.kafka.domain.EventWrapper;
import com.brainstation23.kafka.producer.CommonProducer;
import com.brainstation23.kafka.utils.KafkaUtil;
import com.ecommerce.userauth.domain.event.NotificationEvent;
import com.ecommerce.userauth.domain.event.UserSignUpEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class NotificationService {
    @Value("${bs.kafka.topic.notification}")
    private String topic;
    @Value("${bs.kafka.topic.user-sign-up}")
    private String signUpTopic;

    private final CommonProducer commonProducer;

    public void sendMsg(NotificationEvent notificationEvent) {
        EventWrapper<Object> eventObject = KafkaUtil.prepareKafkaObject(UUID.randomUUID().toString(), Timestamp.from(Instant.now()), notificationEvent);
        commonProducer.sendMessage(topic, eventObject);
    }

    public void sendMsg(UserSignUpEvent userSignUpEvent) {
        EventWrapper<Object> eventObject = KafkaUtil.prepareKafkaObject(UUID.randomUUID().toString(), Timestamp.from(Instant.now()), userSignUpEvent);

        commonProducer.sendMessage(signUpTopic, eventObject);
    }

}
