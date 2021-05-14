package com.training.sportsbetting.view.bet.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

public class WagerTicket {

    private Long outcomeId;
    private Long playerId;
    @Min(value = 1, message = "Bet amount must be more than zero.")
    private BigDecimal amount;

    public WagerTicket() {
        super();
    }

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
