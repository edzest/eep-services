package org.edzest.eep.models;

import lombok.Data;

import java.util.List;

/**
 * This is the DTO for a question that includes the question text and its associated options
 */
@Data
public class QuestionsBody {
    private Long questionId;
    private String questionTxt;
    private List<String> options;
}
