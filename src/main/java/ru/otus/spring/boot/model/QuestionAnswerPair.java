package ru.otus.spring.boot.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerPair {

    @CsvBindByName(column = "question")
    private String question;

    @CsvBindByName(column = "answer")
    private String answer;
}
