package com.training.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "football_sport_event")
public class FootballSportEvent extends SportEvent {

    public FootballSportEvent() {
        super();
    }

    public FootballSportEvent(String title, String homeTeam, String awayTeam, LocalDateTime startTime, LocalDateTime endTime, List<Bet> bets) {
        super(title, homeTeam, awayTeam, startTime, endTime, bets);
    }

}
