package org.edzest.eep.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tests")
@Data
public class TestInfo {
    @Id
    private Long id;
    private String title;
    private String instructions;

    public TestInfo() {
    }

    public TestInfo(Long id, String title, String instructions) {
        this.id = id;
        this.title = title;
        this.instructions = instructions;
    }
}
