package ru.otus.spring.boot.service;

import ru.otus.spring.boot.model.QuestionAnswerPair;

import java.util.List;

public interface QuizService {


    List<QuestionAnswerPair> loadQuestions();
}
