package ru.otus.spring.boot.commandlinerunners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.boot.service.InteractiveService;


@Slf4j
@Component
@RequiredArgsConstructor
public class PreparationDev implements CommandLineRunner {

    private final InteractiveService interactiveService;

    @Override
    public void run(String... args) {
        log.info("Starting testing user");
        interactiveService.testUser();
    }
}
