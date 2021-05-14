package com.training.jsonparser.domain;

import java.util.List;

public class Bet {

    private String description;
    private BetType betType;
    private List<Outcome> outcomes;

    public Bet(String description, BetType betType, List<Outcome> outcomes) {
        super();
        this.description = description;
        this.betType = betType;
        this.outcomes = outcomes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BetType getBetType() {
        return betType;
    }

    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

}
