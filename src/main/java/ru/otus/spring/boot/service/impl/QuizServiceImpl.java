package ru.otus.spring.boot.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.boot.config.i18n.I18nProperties;
import ru.otus.spring.boot.dao.CsvDataLoader;
import ru.otus.spring.boot.model.QuestionAnswerPair;
import ru.otus.spring.boot.service.QuizService;

import java.io.InputStreamReader;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final CsvDataLoader csvDataLoader;

    private final MessageSource messageSource;
    private final I18nProperties i18nProperties;

    public QuizServiceImpl(CsvDataLoader csvDataLoader, MessageSource messageSource, I18nProperties i18nProperties) {
        this.csvDataLoader = csvDataLoader;
        this.messageSource = messageSource;
        this.i18nProperties = i18nProperties;
    }

    @Override
    public List<QuestionAnswerPair> loadQuestions() {
        final var inputStream = csvDataLoader.loadData(messageSource.getMessage(i18nProperties.getQuizKeys().getPath(), null, i18nProperties.getLocale()));
        return new CsvToBeanBuilder<QuestionAnswerPair>(new InputStreamReader(inputStream))
                .withType(QuestionAnswerPair.class)
                .build()
                .parse();
    }
}
