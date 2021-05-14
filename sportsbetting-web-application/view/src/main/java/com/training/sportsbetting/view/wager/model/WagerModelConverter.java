package com.training.sportsbetting.view.wager.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.Wager;

@Service
public class WagerModelConverter {

    @Autowired
    private WagerModelBuilder wagerModelBuilder;

    public List<WagerModel> toModel(List<Wager> wagers) {
        return wagers.stream().map(wager -> toModel(wager)).collect(Collectors.toList());
    }

    public WagerModel toModel(Wager wager) {
        return wagerModelBuilder
                .setTitle(wager.getOutcome().getBet().getSportEvent().getTitle())
                .setTime(wager.getOutcome().getBet().getSportEvent().getStartTime(), wager.getOutcome().getBet().getSportEvent().getEndTime())
                .setOutcomeDescription(wager.getOutcome().getDescription())
                .setAmount(wager.getAmount())
                .setOutcomeOdd(wager.getOutcome().getValue())
                .setProcessed(wager.isProcessed())
                .setWin(wager.isWin())
                .build();
    }
}
