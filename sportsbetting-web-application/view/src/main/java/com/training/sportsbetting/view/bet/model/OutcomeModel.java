package com.training.sportsbetting.view.bet.model;

public final class OutcomeModel {

    private final Long id;
    private final String title;
    private final String time;
    private final String betType;
    private final String betDescreption;
    private final String outcomeDescription;
    private final Double outcomeOdd;

    public OutcomeModel(Long id, String title, String time, String betType, String betDescreption, String outcomeDescription, Double outcomeOdd) {
        super();
        this.id = id;
        this.title = title;
        this.time = time;
        this.betType = betType;
        this.betDescreption = betDescreption;
        this.outcomeDescription = outcomeDescription;
        this.outcomeOdd = outcomeOdd;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getBetType() {
        return betType;
    }

    public String getBetDescreption() {
        return betDescreption;
    }

    public String getOutcomeDescription() {
        return outcomeDescription;
    }

    public Double getOutcomeOdd() {
        return outcomeOdd;
    }

}
