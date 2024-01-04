package ru.otus.spring.boot.handler;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInputHandler {

    private final Scanner scanner = new Scanner(System.in);

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void println(String message) {
        System.out.println(message);
    }
}
