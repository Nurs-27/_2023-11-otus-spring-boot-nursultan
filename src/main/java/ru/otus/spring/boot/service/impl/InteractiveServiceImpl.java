package ru.otus.spring.boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.boot.config.i18n.I18nProperties;
import ru.otus.spring.boot.handler.ConsoleInputHandler;
import ru.otus.spring.boot.model.QuestionAnswerPair;
import ru.otus.spring.boot.model.User;
import ru.otus.spring.boot.service.AnnounceService;
import ru.otus.spring.boot.service.InteractiveService;
import ru.otus.spring.boot.service.QuizService;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class InteractiveServiceImpl implements InteractiveService {

    private final QuizService quizService;

    private final AnnounceService announceService;

    private final ConsoleInputHandler consoleInputHandler;

    private final MessageSource messageSource;

    private final I18nProperties i18nProperties;

    @Override
    public void testUser() {
        final var user = requestInitialUserInformation();
        AtomicInteger correctAnswerCount = new AtomicInteger();

        quizService.loadQuestions()
                .forEach(qap -> {
                    final var enteredAnswer = consoleInputHandler.readLine(qap.getQuestion());
                    checkEnteredAnswer(qap, enteredAnswer, correctAnswerCount);
                });

        announceService.announceQuizResult(user, Integer.parseInt(correctAnswerCount.toString()));
    }

    private User requestInitialUserInformation() {
        final String firstName = consoleInputHandler.readLine(
                messageSource.getMessage(i18nProperties.getQuizKeys().getRegistration().get("firstName"), null, i18nProperties.getLocale())
        );
        final String lastName = consoleInputHandler.readLine(
                messageSource.getMessage(i18nProperties.getQuizKeys().getRegistration().get("lastName"), null, i18nProperties.getLocale())
        );

        return new User(firstName, lastName);
    }

    private void checkEnteredAnswer(QuestionAnswerPair qap, String enteredAnswer, AtomicInteger correctAnswerCount) {
        if (qap.getAnswer().equalsIgnoreCase(enteredAnswer)) {
            correctAnswerCount.getAndIncrement();
        }
    }
}
