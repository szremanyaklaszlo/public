package com.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.hibernate.annotations.ColumnDefault;

import com.training.sportsbetting.domain.converter.LocalDateTimeAttributeConverter;

@Entity
public class Wager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(1)
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(name = "valid_from", nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime validFrom;
    @Column(name = "valid_until")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime validUntil;
    @ManyToOne(optional = false)
    @JoinColumn(name = "outcome_id", nullable = false)
    private Outcome outcome;
    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @ColumnDefault(value = "0")
    private boolean processed;
    @ColumnDefault(value = "0")
    private boolean win;

    public Wager() {
    }

    public Wager(BigDecimal amount, LocalDateTime validFrom, LocalDateTime validUntil, Outcome outcome, Player player, Currency currency) {
        super();
        this.amount = amount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.outcome = outcome;
        this.player = player;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public Player getPlayer() {
        return player;
    }

    public Currency getCurrency() {
        return currency;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

}
