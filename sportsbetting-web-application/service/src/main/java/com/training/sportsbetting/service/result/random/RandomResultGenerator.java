package com.training.sportsbetting.service.result.random;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.SportEvent;
import com.training.sportsbetting.service.result.ResultGenerator;
import com.training.sportsbetting.service.result.SportEventResult;

@Service("RandomResultGenerator")
public class RandomResultGenerator implements ResultGenerator {

    private final static int MAX_GOAL = 3;
    private final static int MIN_GOAL = 0;

    @Override
    public SportEventResult generateResult(SportEvent sportEvent) {
        int homeTeamGoals = generateGoals();
        int awayTeamGoals = generateGoals();
        String winner = getWinner(homeTeamGoals, awayTeamGoals, sportEvent);
        String finalScore = homeTeamGoals + ":" + awayTeamGoals;
        return new SportEventResult(finalScore, winner);
    }

    private int generateGoals() {
        return new Random().ints(1, MIN_GOAL, MAX_GOAL + 1).findFirst().getAsInt();
    }

    private String getWinner(int homeTeamGoals, int awayTeamGoals, SportEvent sportEvent) {
        String winner;
        if (homeTeamGoals > awayTeamGoals) {
            winner = sportEvent.getHomeTeam();
        } else if (awayTeamGoals > homeTeamGoals) {
            winner = sportEvent.getAwayTeam();
        } else {
            winner = "Draw";
        }
        return winner;
    }

}
