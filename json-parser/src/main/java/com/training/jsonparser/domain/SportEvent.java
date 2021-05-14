package com.training.jsonparser.domain;

import java.time.LocalDateTime;
import java.util.List;

public class SportEvent {
    
    private String title;
    private LocalDateTime startTime;
    private List<Bet> bets;
    
    public SportEvent(String title, LocalDateTime startTime, List<Bet> bets) {
        super();
        this.title = title;
        this.startTime = startTime;
        this.bets = bets;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
    
}
