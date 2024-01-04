package ru.otus.spring.boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.boot.config.i18n.I18nProperties;
import ru.otus.spring.boot.service.MessageService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;

    private final I18nProperties i18nProperties;

    public String getMessage(String key, Object[] params, String defaultMsg) {
        return Optional.of(messageSource.getMessage(key, params, i18nProperties.getLocale()))
                .orElse(defaultMsg);
    }
}
