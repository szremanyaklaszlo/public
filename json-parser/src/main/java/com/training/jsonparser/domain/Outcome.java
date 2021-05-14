package com.training.jsonparser.domain;

import java.util.List;

public class Outcome {

    private String description;
    private List<OutcomeOdd> outcomeOdds;

    public Outcome(String description, List<OutcomeOdd> outcomeOdds) {
        super();
        this.description = description;
        this.outcomeOdds = outcomeOdds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }

    public void setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
        this.outcomeOdds = outcomeOdds;
    }

}
