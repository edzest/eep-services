package org.edzest.eep.models.scores;

import lombok.Data;

@Data
public class SimpleScore {
    private int scored;
    private int outOf;

    public SimpleScore(int scored, int outOf) {
        this.scored = scored;
        this.outOf = outOf;
    }

    public SimpleScore() {
    }

    public void incrementScored() {
        scored++;
    }
}
