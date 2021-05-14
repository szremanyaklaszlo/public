package com.training.sportsbetting.view.bet.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.Outcome;

@Service
public class OutcomeModelConverter {
    
    @Autowired
    private OutcomeModelBuilder modelBuilder;
    
    public OutcomeModel toModel(Outcome outcome) {
        return modelBuilder
                .setId(outcome.getId())
                .setTitle(outcome.getBet().getSportEvent().getTitle())
                .setTime(outcome.getBet().getSportEvent().getStartTime(), outcome.getBet().getSportEvent().getEndTime())
                .setBetType(outcome.getBet().getBetType())
                .setBetDescreption(outcome.getBet().getDescription())
                .setOutcomeDescription(outcome.getDescription())
                .setOutcomeOdd(outcome.getValue())
                .build();
    }
    
    public List<OutcomeModel> toModel(List<Outcome> outcomes) {
        return outcomes.stream().map(outcome -> toModel(outcome)).collect(Collectors.toList());
    }

}
