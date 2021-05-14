package com.training.sportsbetting.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Bet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "bet_type", nullable = false)
    private BetType betType;
    private String description;
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "sport_event_id", nullable = false)
    private SportEvent sportEvent;
    @JsonManagedReference
    @OneToMany(mappedBy = "bet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Outcome> outcomes;

    public Bet() {
    }

    public Bet(BetType betType, String description, SportEvent sportEvent) {
        this.betType = betType;
        this.description = description;
        this.sportEvent = sportEvent;
    }

    public long getId() {
        return id;
    }

    public BetType getBetType() {
        return betType;
    }

    public String getDescription() {
        return description;
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

}
