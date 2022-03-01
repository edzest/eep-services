package org.edzest.eep.models;

import lombok.Data;
import org.edzest.eep.models.answers.Answer;

import java.util.List;

@Data
public class TestResultRequest {
    private Long testId;
    private String studentName;
    private List<Answer> answers;
}
