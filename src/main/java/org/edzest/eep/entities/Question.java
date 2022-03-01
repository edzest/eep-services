package org.edzest.eep.entities;

import lombok.Data;

import javax.persistence.*;

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
}
