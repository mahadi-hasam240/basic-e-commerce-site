package com.brainstation23.kafka.utils;

import com.brainstation23.kafka.domain.EventWrapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author A. S. M. Tarek
 * Created on: 22 Jan, 2023
 */
@Component
public class KafkaUtil {
    public static EventWrapper<Object> prepareKafkaObject(String requestId, Timestamp timestamp, Object data) {
        return new EventWrapper<>(requestId, timestamp, data);
    }
}
