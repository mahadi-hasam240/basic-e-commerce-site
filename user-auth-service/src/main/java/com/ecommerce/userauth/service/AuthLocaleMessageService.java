package com.ecommerce.userauth.service;

import com.ecommerce.userauth.common.exceptions.RecordNotFoundException;
import com.ecommerce.userauth.domain.common.LocaleMessageDto;
import com.ecommerce.userauth.domain.entity.LocaleMessage;
import com.ecommerce.userauth.domain.entity.redis.Message;
import com.ecommerce.userauth.domain.enums.ResponseMessage;
import com.ecommerce.userauth.repository.LocaleMessageRepository;
import com.ecommerce.userauth.repository.redis.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthLocaleMessageService extends BaseService {

    private final LocaleMessageRepository localeMessageRepository;
    private final MessageRepository messageRepository;

    public LocaleMessageDto getLocaleData(String locale) {

        LocaleMessage localeMessage = localeMessageRepository.findLocaleMessageByLocale(locale);

        if (Objects.isNull(localeMessage))
            throw new RecordNotFoundException(ResponseMessage.LOCALE_RECORD_NOT_FOUND);

        return new LocaleMessageDto(localeMessage.getLocale(), localeMessage.getContent());
    }

    public Optional<Message> findById(String id) {
        return messageRepository.findById(id);
    }

}
