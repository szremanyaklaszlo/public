package com.training.sportsbetting.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    @Column(nullable = false)
    private BigDecimal value;
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "bet_id", nullable = false)
    private Bet bet;

    public Outcome() {
    }

    public Outcome(String description, BigDecimal value, Bet bet) {
        super();
        this.description = description;
        this.value = value;
        this.bet = bet;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Bet getBet() {
        return bet;
    }

}
