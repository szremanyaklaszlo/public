package com.training.jsonparser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.training.jsonparser.domain.Bet;
import com.training.jsonparser.domain.BetType;
import com.training.jsonparser.domain.Outcome;
import com.training.jsonparser.domain.OutcomeOdd;
import com.training.jsonparser.domain.SportEvent;
import com.training.jsonparser.service.GsonParser;

public class App {

    public static void main(String[] args) {
        List<SportEvent> sportEvents = initializeSportEvents();
        GsonParser gsonParser = new GsonParser();
        gsonParser.writeSportEvent(sportEvents);
        List<SportEvent> sportEventsFromJson = gsonParser.readSportEvent("football.json");
        System.out.println(sportEventsFromJson);
    }

    private static List<SportEvent> initializeSportEvents() {
        List<SportEvent> sportEvents = Arrays.asList(new SportEvent("Arsenal vs Chelsea", LocalDateTime.now(),
                Arrays.asList(new Bet("The winner of the game", BetType.WINNER,
                        Arrays.asList(new Outcome("Arsenal",
                                Arrays.asList(new OutcomeOdd(LocalDateTime.parse("2007-12-03T10:15:30"), LocalDateTime.parse("2007-12-03T10:15:30"),
                                        BigDecimal.TEN))),
                                new Outcome("Chelsea",
                                        Arrays.asList(new OutcomeOdd(LocalDateTime.parse("2007-12-03T10:15:30"), LocalDateTime.parse("2007-12-03T10:15:30"),
                                                BigDecimal.TEN))))),
                        new Bet("Final score", BetType.FINAL_SCORE,
                                Arrays.asList(new Outcome("Arsenal",
                                        Arrays.asList(new OutcomeOdd(LocalDateTime.parse("2007-12-03T10:15:30"), LocalDateTime.parse("2007-12-03T10:15:30"),
                                                BigDecimal.TEN))),
                                        new Outcome("Chelsea",
                                                Arrays.asList(new OutcomeOdd(LocalDateTime.parse("2007-12-03T10:15:30"),
                                                        LocalDateTime.parse("2007-12-03T10:15:30"), BigDecimal.TEN))))))),
                new SportEvent("Arsenal vs Chelsea", LocalDateTime.now(),
                        Arrays.asList(new Bet("The winner of the game", BetType.WINNER,
                                Arrays.asList(new Outcome("Arsenal",
                                        Arrays.asList(new OutcomeOdd(LocalDateTime.parse("2007-12-03T10:15:30"), LocalDateTime.parse("2007-12-03T10:15:30"),
                                                BigDecimal.TEN))),
                                        new Outcome("Chelsea",
                                                Arrays.asList(new OutcomeOdd(LocalDateTime.parse("2007-12-03T10:15:30"),
                                                        LocalDateTime.parse("2007-12-03T10:15:30"), BigDecimal.TEN))))),
                                new Bet("Final score", BetType.FINAL_SCORE,
                                        Arrays.asList(new Outcome("Arsenal",
                                                Arrays.asList(new OutcomeOdd(LocalDateTime.parse("2007-12-03T10:15:30"),
                                                        LocalDateTime.parse("2007-12-03T10:15:30"), BigDecimal.TEN))),
                                                new Outcome("Chelsea",
                                                        Arrays.asList(new OutcomeOdd(LocalDateTime.parse("2007-12-03T10:15:30"),
                                                                LocalDateTime.parse("2007-12-03T10:15:30"), BigDecimal.TEN))))))));
        return sportEvents;
    }
}
