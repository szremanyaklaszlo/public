package com.training.sportsbetting.view.wager.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class WagerModelBuilder {

    private final static DateTimeFormatter START_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final static DateTimeFormatter END_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    
    private String title;
    private String time;
    private String outcomeDescription;
    private BigDecimal amount;
    private BigDecimal outcomeOdd;
    private boolean isProcessed;
    private boolean isWin;

    public WagerModelBuilder() {
        super();
    }

    public WagerModelBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public WagerModelBuilder setTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.time = startTime.format(START_FORMAT) +
                "-" + endTime.format(END_FORMAT);
        return this;
    }

    public WagerModelBuilder setOutcomeDescription(String outcomeDescription) {
        this.outcomeDescription = outcomeDescription;
        return this;
    }

    public WagerModelBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    
    public WagerModelBuilder setOutcomeOdd(BigDecimal outcomeOdd) {
        this.outcomeOdd = outcomeOdd;
        return this;
    }

    public WagerModelBuilder setProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
        return this;
    }

    public WagerModelBuilder setWin(boolean isWin) {
        this.isWin = isWin;
        return this;
    }

    public WagerModel build() {
        return new WagerModel(this.title, this.time, this.outcomeDescription, this.amount, this.outcomeOdd, this.isProcessed, this.isWin);
    }
}
