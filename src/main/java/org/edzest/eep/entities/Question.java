package org.edzest.eep.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "questions")
@Data
public class Question {
    @Id
    private Long questionId;

    @ManyToOne()
    @JoinColumn(name = "testId")
    private TestInfo test;

    private String questionTxt;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctOption;
    private String explanation;

    public Question() {
    }

    public Question(Long questionId, TestInfo test, String questionTxt, String option1, String option2, String option3, String option4, String correctOption, String explanation) {
        this.questionId = questionId;
        this.test = test;
        this.questionTxt = questionTxt;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.explanation = explanation;
    }

    public List<String> getAllOptions() {
        return Stream.of(option1, option2, option3, option4)
                .filter(Objects::nonNull).collect(Collectors.toList());
    }
}
