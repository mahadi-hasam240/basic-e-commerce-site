package com.brainstation23.kafka.producer;

import com.brainstation23.kafka.domain.EventWrapper;
import com.brainstation23.kafka.domain.ParentEvent;
import com.brainstation23.kafka.logger.KafkaServiceLogger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CommonProducer {
    private final KafkaServiceLogger logger;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CommonProducer(KafkaServiceLogger logger, KafkaTemplate<String, Object> kafkaTemplate) {
        this.logger = logger;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void sendMessage(String topicName, EventWrapper<? super ParentEvent> eventWrapper) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, eventWrapper);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.trace("Sent message=[" + eventWrapper + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                logger.trace("Unable to send message=[" + eventWrapper + "] due to : " + ex.getMessage());
            }
        });
    }
}
