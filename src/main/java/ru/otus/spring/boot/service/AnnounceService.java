package ru.otus.spring.boot.service;

import ru.otus.spring.boot.model.User;

public interface AnnounceService {

    void announceQuizResult(User user, int correctAnswerCount);
}
