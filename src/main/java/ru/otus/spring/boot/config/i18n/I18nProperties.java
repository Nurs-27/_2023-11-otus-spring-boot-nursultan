package ru.otus.spring.boot.config.i18n;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;
import java.util.Map;

@Getter
@ConfigurationProperties(prefix = "i18n")

public class I18nProperties {

    private final Locale locale;

    private final QuizKeys quizKeys;

    @ConstructorBinding
    public I18nProperties(Locale locale, QuizKeys quizKeys) {
        this.locale = locale;
        this.quizKeys = quizKeys;
    }

    @Getter
    public static class QuizKeys {
        private final Map<String, String> registration;
        private final String path;
        private final String announceResult;

        public QuizKeys(Map<String, String> registration, String path, String announceResult) {
            this.registration = registration;
            this.path = path;
            this.announceResult = announceResult;
        }
    }
}
