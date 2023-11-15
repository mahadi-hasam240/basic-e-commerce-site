package com.ecommerce.userauth.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BNLocalMessageReader {
    private static Map<String, String> messages;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = BNLocalMessageReader.class.getResourceAsStream("/i18n/messages_bn.json")) {
            messages = objectMapper.readValue(inputStream, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(String key) {
        return messages.get(key);
    }
}
