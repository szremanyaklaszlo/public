package com.training.sportsbetting.view.events.model;

import java.math.BigDecimal;

public final class SportEventModel {

    private final Long id;
    private final String title;
    private final String time;
    private final BigDecimal homeTeamWinOdd;
    private final BigDecimal drawOdd;
    private final BigDecimal awayTeamWinOdd;
    private final Short numberOfOutcomes;

    SportEventModel(Long id, String title, String time, BigDecimal homeTeamWinOdd, BigDecimal drawOdd, BigDecimal awayTeamWinOdd, Short numberOfOutcomes) {
        super();
        this.id = id;
        this.title = title;
        this.time = time;
        this.homeTeamWinOdd = homeTeamWinOdd;
        this.drawOdd = drawOdd;
        this.awayTeamWinOdd = awayTeamWinOdd;
        this.numberOfOutcomes = numberOfOutcomes;
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

    public BigDecimal getHomeTeamWinOdd() {
        return homeTeamWinOdd;
    }

    public BigDecimal getDrawOdd() {
        return drawOdd;
    }

    public BigDecimal getAwayTeamWinOdd() {
        return awayTeamWinOdd;
    }

    public Short getNumberOfOutcomes() {
        return numberOfOutcomes;
    }

}
