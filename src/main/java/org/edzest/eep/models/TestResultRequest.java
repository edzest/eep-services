package org.edzest.eep.models;

import lombok.Data;
import org.edzest.eep.models.answers.SingleChoiceAnswer;

import java.util.List;

@Data
public class TestResultRequest {
    private Long testId;
    private String studentName;
    private List<SingleChoiceAnswer> answers;
}
