package org.edzest.eep.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tests")
@Data
public class TestInfo {

    public TestInfo(Long testId, String title, String instructions) {
        this.testId = testId;
        this.title = title;
        this.instructions = instructions;
    }

    @Id
    private Long testId;
    private String title;
    private String instructions;
}
