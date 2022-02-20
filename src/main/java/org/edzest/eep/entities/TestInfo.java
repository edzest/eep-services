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
    private Long testId;
    private String title;
    private String instructions;
}
