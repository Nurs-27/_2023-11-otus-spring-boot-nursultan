package ru.otus.spring.boot.service;

public interface MessageService {

    String getMessage(String key, Object[] params, String defaultMsg);
}
