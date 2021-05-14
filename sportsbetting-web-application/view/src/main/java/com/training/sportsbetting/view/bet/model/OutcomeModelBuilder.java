package com.training.sportsbetting.view.bet.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.BetType;

@Service
public class OutcomeModelBuilder {

    private final static DateTimeFormatter START_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final static DateTimeFormatter END_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    private Long id;
    private String title;
    private String time;
    private String betType;
    private String betDescreption;
    private String outcomeDescription;
    private Double outcomeOdd;

    public OutcomeModelBuilder() {
        super();
    }

    public OutcomeModelBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public OutcomeModelBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public OutcomeModelBuilder setTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.time = startTime.format(START_TIME_FORMAT) +
                "-" + endTime.format(END_TIME_FORMAT);
        return this;
    }

    public OutcomeModelBuilder setBetType(BetType betType) {
        this.betType = betType.name();
        return this;
    }

    public OutcomeModelBuilder setBetDescreption(String betDescreption) {
        this.betDescreption = betDescreption;
        return this;
    }

    public OutcomeModelBuilder setOutcomeDescription(String outcomeDescription) {
        this.outcomeDescription = outcomeDescription;
        return this;
    }

    public OutcomeModelBuilder setOutcomeOdd(BigDecimal outcomeOdd) {
        if(outcomeOdd == null) {
            throw new NullPointerException("Outcome odd cannot be null!");
        }
        this.outcomeOdd = outcomeOdd.round(new MathContext(3)).doubleValue();
        return this;
    }

    public OutcomeModel build() {
        return new OutcomeModel(this.id, this.title, this.time, this.betType, this.betDescreption, this.outcomeDescription, this.outcomeOdd);
    }

}
