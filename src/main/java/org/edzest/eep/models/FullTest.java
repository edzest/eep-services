package org.edzest.eep.models;

import lombok.Data;

import java.util.List;

@Data
public class FullTest {
    private Long testId;
    private String title;
    private String instructions;
    private List<QuestionsBody> questions;
}
