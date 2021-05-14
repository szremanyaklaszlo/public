package com.training.jsonparser.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {

    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private BigDecimal value;

    public OutcomeOdd(LocalDateTime validFrom, LocalDateTime validUntil, BigDecimal value) {
        super();
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.value = value;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
