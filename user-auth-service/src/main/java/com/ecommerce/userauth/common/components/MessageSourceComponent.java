package com.ecommerce.userauth.common.components;

import com.ecommerce.userauth.common.logger.CustomerAuthServiceLogger;
import com.ecommerce.userauth.domain.common.LocaleMessageDto;
import com.ecommerce.userauth.domain.entity.redis.Message;
import com.ecommerce.userauth.domain.enums.SpecialCharsEnum;
import com.ecommerce.userauth.service.AuthLocaleMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component("messageSource")
public class MessageSourceComponent extends AbstractMessageSource {

    private final AuthLocaleMessageService authLocaleMessageService;
    private final CustomerAuthServiceLogger authLogger;

    public MessageSourceComponent(@Lazy AuthLocaleMessageService authLocaleMessageService,
                                  CustomerAuthServiceLogger logger) {
        this.authLocaleMessageService= authLocaleMessageService;
        this.authLogger = logger;
    }

    @Override
    protected MessageFormat resolveCode(String key, Locale locale) {
        String id = key.concat(SpecialCharsEnum.COLON.getSign()).concat(locale.getLanguage());

        Optional<Message> messageOpt = authLocaleMessageService.findById(id);

        if (messageOpt.isPresent())
            return new MessageFormat(messageOpt.get().getContent(), locale);

        List<Message> messageList = getLocaleMessage(locale.getLanguage());
        authLogger.trace("Locale message list:->" + new Gson().toJson(messageList) + "\n");
        Optional<Message> targetMessageOpt = getTargetMessage(id, messageList);

        if (targetMessageOpt.isEmpty()) {
            authLogger.trace("\n\t-------Message Not found--------\n");
            return new MessageFormat("Something went wrong", locale);
        }

        Message targetMessage = targetMessageOpt.get();

        return new MessageFormat(targetMessage.getContent(), locale);
    }

    private Optional<Message> getTargetMessage(String id, List<Message> messageList) {
        return messageList.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    private List<Message> getLocaleMessage(String locale) {
       LocaleMessageDto localeMessageDto = authLocaleMessageService.getLocaleData(locale);

        String jsonContent = localeMessageDto.getContent();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map<String, String> map = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });

            List<Message> messages = map
                    .entrySet()
                    .stream()
                    .map(this::prepareEntity)
                    .collect(Collectors.toList());

            return messages
                    .stream()
                    .map(item -> {
                        item.setLocale(locale);
                        item.setId(item.getKey().concat(SpecialCharsEnum.COLON.getSign()).concat(locale));
                        return item;
                    }).collect(Collectors.toList());

        } catch (JsonProcessingException ex) {
            authLogger.error(ex.getMessage(), ex);
        }

        return Collections.emptyList();
    }

    private Message prepareEntity(Map.Entry<String, String> mapEntry) {
        Message message = new Message();
        message.setKey(mapEntry.getKey());
        message.setContent(mapEntry.getValue());
        return message;
    }
}
