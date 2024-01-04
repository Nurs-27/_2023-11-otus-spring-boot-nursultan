package ru.otus.spring.boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.boot.config.i18n.I18nProperties;
import ru.otus.spring.boot.model.User;
import ru.otus.spring.boot.service.AnnounceService;

@Service
@RequiredArgsConstructor
public class AnnounceServiceImpl implements AnnounceService {

    private final MessageSource messageSource;

    private final I18nProperties i18nProperties;

    @Override
    public void announceQuizResult(User user, int correctAnswerCount) {
        final var stringFormat = messageSource.getMessage(i18nProperties.getQuizKeys().getAnnounceResult(), null, i18nProperties.getLocale());
        System.out.printf(stringFormat, user.getFirstName(), user.getLastName(), correctAnswerCount);
    }
}
