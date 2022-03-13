package org.edzest.eep.models.questions;

import lombok.Data;

import java.util.List;

@Data
public class TestResultResponseQuestion {
    private Long questionId;
    private String questionTxt;
    protected List<String> options;
    private String correctOption;
    private String selectedOption;
    private String explanation;

    public TestResultResponseQuestion() {
    }

    public TestResultResponseQuestion(Long questionId, String questionTxt, List<String> options, String correctOption, String selectedOption,String explanation) {
        this.questionId = questionId;
        this.questionTxt = questionTxt;
        this.options = options;
        this.correctOption = correctOption;
        this.selectedOption = selectedOption;
        this.explanation = explanation;
    }
}
