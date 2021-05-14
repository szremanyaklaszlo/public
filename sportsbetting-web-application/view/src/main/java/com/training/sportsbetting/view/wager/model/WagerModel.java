package com.training.sportsbetting.view.wager.model;

import java.math.BigDecimal;

public class WagerModel {

    private String title;
    private String time;
    private String outcomeDescription;
    private BigDecimal amount;
    private BigDecimal outcomeOdd;
    private boolean isProcessed;
    private boolean isWin;

    public WagerModel(String title, String time, String outcomeDescription, BigDecimal amount, BigDecimal outcomeOdd, boolean isProcessed, boolean isWin) {
        super();
        this.title = title;
        this.time = time;
        this.outcomeDescription = outcomeDescription;
        this.amount = amount;
        this.outcomeOdd = outcomeOdd;
        this.isProcessed = isProcessed;
        this.isWin = isWin;
    }

    public WagerModel() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getOutcomeDescription() {
        return outcomeDescription;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getOutcomeOdd() {
        return outcomeOdd;
    }

    public boolean getIsProcessed() {
        return isProcessed;
    }

    public boolean getIsWin() {
        return isWin;
    }

}
