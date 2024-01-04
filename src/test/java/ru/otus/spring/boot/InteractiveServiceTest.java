package ru.otus.spring.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.boot.config.i18n.I18nProperties;
import ru.otus.spring.boot.handler.ConsoleInputHandler;
import ru.otus.spring.boot.model.QuestionAnswerPair;
import ru.otus.spring.boot.model.User;
import ru.otus.spring.boot.service.AnnounceService;
import ru.otus.spring.boot.service.QuizService;
import ru.otus.spring.boot.service.impl.InteractiveServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InteractiveServiceTest {

    @Mock
    private QuizService quizService;

    @Mock
    private AnnounceService announceService;

    @Mock
    private ConsoleInputHandler consoleInputHandler;

    @Mock
    private MessageSource messageSource;

    @Spy
    private I18nProperties i18nProperties;

    @Spy
    private I18nProperties.QuizKeys quizKeys;

    @InjectMocks
    private InteractiveServiceImpl interactiveService;


    private final User testUser = new User("TestFirstName", "TestLastName");
    private final List<QuestionAnswerPair> qapList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        final var qap1 = new QuestionAnswerPair("Сколько букв в русском алфавите", "33");
        final var qap2 = new QuestionAnswerPair("Сколько букв в английском алфавите", "26");

        qapList.add(qap1);
        qapList.add(qap2);

        Locale testLocale = Locale.forLanguageTag("en"); // Or another locale as required

        Map<String, String> registrationKeys = new HashMap<>();
        registrationKeys.put("firstName", "registration.first.name");
        registrationKeys.put("lastName", "registration.last.name");

        quizKeys = new I18nProperties.QuizKeys(
                registrationKeys,
                "quiz.path",
                "announce.result"
        );

        i18nProperties = new I18nProperties(testLocale, quizKeys);

        when(i18nProperties.getQuizKeys()).thenReturn(quizKeys);
        when(quizKeys.getRegistration()).thenReturn(registrationKeys);
        when(quizKeys.getPath()).thenReturn(null);
        when(quizKeys.getAnnounceResult()).thenReturn(null);
    }


    @Test
    void shouldCallAnnounceMethodWith1CorrectAnswer() {
        when(consoleInputHandler.readLine(i18nProperties.getQuizKeys().getRegistration().get("firstName"))).thenReturn(testUser.getFirstName());
        when(consoleInputHandler.readLine(i18nProperties.getQuizKeys().getRegistration().get("lastName"))).thenReturn(testUser.getLastName());

        when(quizService.loadQuestions())
                .thenReturn(qapList);

        when(consoleInputHandler.readLine("Сколько букв в русском алфавите")).thenReturn("32");
        when(consoleInputHandler.readLine("Сколько букв в английском алфавите")).thenReturn("26");

        interactiveService.testUser();

        verify(announceService, times(1)).announceQuizResult(testUser, 1);
    }
}
