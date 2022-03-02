package org.edzest.eep.models;

import lombok.Data;
import org.edzest.eep.models.questions.TestResultResponseQuestion;
import org.edzest.eep.models.scores.SimpleScore;

import java.util.List;

@Data
public class TestResultResponse {
    private Long testId;
    private String studentName;
    private SimpleScore scores;
    private List<TestResultResponseQuestion> questions;

    public TestResultResponse() {
    }

    public TestResultResponse(Long testId, String studentName, SimpleScore scores, List<TestResultResponseQuestion> questions) {
        this.testId = testId;
        this.studentName = studentName;
        this.scores = scores;
        this.questions = questions;
    }
}
