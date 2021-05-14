package com.training.sportsbetting.view.events.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.Bet;
import com.training.sportsbetting.domain.BetType;
import com.training.sportsbetting.domain.Outcome;
import com.training.sportsbetting.domain.SportEvent;

@Service
public class SportEventModelConverter {

    @Autowired
    private SportEventModelBuilder sportEventModelBuilder;

    public List<SportEventModel> toModel(List<SportEvent> sportEvents) {
        return sportEvents.stream().map(sportEvent -> toModel(sportEvent)).collect(Collectors.toList());
    }

    public SportEventModel toModel(SportEvent sportEvent) {
        return sportEventModelBuilder
                .setId(sportEvent.getId())
                .setTitle(sportEvent.getTitle())
                .setTime(sportEvent.getStartTime(), sportEvent.getEndTime())
                .setHomeWinOdd(getOutcomeOddByDescription(sportEvent, sportEvent.getHomeTeam()))
                .setDrawOdd(getOutcomeOddByDescription(sportEvent, "Draw"))
                .setAwayWinOdd(getOutcomeOddByDescription(sportEvent, sportEvent.getAwayTeam()))
                .setNumberOfOutcomes(countOutcomes(sportEvent))
                .build();
    }

    private Optional<BigDecimal> getOutcomeOddByDescription(SportEvent sportEvent, String winnerDescription) {
        return sportEvent.getBets().stream()
                .filter(bet -> bet.getBetType() == BetType.WINNER)
                .map(Bet::getOutcomes)
                .flatMap(List::stream)
                .filter(outcome -> winnerDescription.equals(outcome.getDescription()))
                .findFirst()
                .map(Outcome::getValue);
    }

    private Short countOutcomes(SportEvent sportEvent) {
        return (short) sportEvent.getBets().stream()
                .map(Bet::getOutcomes)
                .flatMap(List::stream)
                .count();
    }

}
