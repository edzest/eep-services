package org.edzest.eep.models;

import lombok.Data;

import java.util.List;

@Data
public class FullTest {
    private Long testId;
    private String title;
    private String instructions;
    private List<QuestionsBody> questions;

    public FullTest() {
    }

    public FullTest(Long testId, String title, String instructions, List<QuestionsBody> questions) {
        this.testId = testId;
        this.title = title;
        this.instructions = instructions;
        this.questions = questions;
    }
}
