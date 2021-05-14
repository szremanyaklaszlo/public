package com.training.sportsbetting.view.events.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class SportEventModelBuilder {

    private final static DateTimeFormatter START_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final static DateTimeFormatter END_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    private Long id;
    private String title;
    private String time;
    private BigDecimal homeWinOdd;
    private BigDecimal drawOdd;
    private BigDecimal awayWinOdd;
    private Short numberOfOutcomes;

    public SportEventModelBuilder() {
        super();
    }

    public SportEventModelBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public SportEventModelBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SportEventModelBuilder setTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.time = startTime.format(START_FORMAT) +
                "-" + endTime.format(END_FORMAT);
        return this;
    }

    public SportEventModelBuilder setHomeWinOdd(Optional<BigDecimal> homeWinOdd) {
        this.homeWinOdd = format(homeWinOdd);
        return this;
    }

    public SportEventModelBuilder setDrawOdd(Optional<BigDecimal> drawOdd) {
        this.drawOdd = format(drawOdd);
        return this;
    }

    public SportEventModelBuilder setAwayWinOdd(Optional<BigDecimal> awayWinOdd) {
        this.awayWinOdd = format(awayWinOdd);
        return this;
    }

    private BigDecimal format(Optional<BigDecimal> number) {
        return number.isPresent() ? number.get().round(new MathContext(3)) : null;
    }

    public SportEventModelBuilder setNumberOfOutcomes(Short numberOfOutcomes) {
        this.numberOfOutcomes = numberOfOutcomes;
        return this;
    }

    public SportEventModel build() {
        return new SportEventModel(
                this.id,
                this.title,
                this.time,
                this.homeWinOdd,
                this.drawOdd,
                this.awayWinOdd,
                this.numberOfOutcomes);
    }
}
