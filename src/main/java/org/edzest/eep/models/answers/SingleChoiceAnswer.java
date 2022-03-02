package org.edzest.eep.models.answers;

import lombok.Data;

@Data
public class SingleChoiceAnswer {
    public SingleChoiceAnswer() {
    }

    public SingleChoiceAnswer(Long questionId, String selectedOption) {
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }

    private Long questionId;
    private String selectedOption;
}
