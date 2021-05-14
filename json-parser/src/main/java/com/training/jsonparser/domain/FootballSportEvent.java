package com.training.jsonparser.domain;

import java.time.LocalDateTime;
import java.util.List;

public class FootballSportEvent extends SportEvent {

    public FootballSportEvent(String title, LocalDateTime startTime, List<Bet> bets) {
        super(title, startTime, bets);
    }

}
