package com.training.sportsbetting.service.result;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.BetType;
import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.domain.Wager;
import com.training.sportsbetting.service.player.PlayerService;
import com.training.sportsbetting.service.wager.WagerService;

@Service
public class WagerEvaluator {
    
    @Autowired
    private WagerService wagerService;
    @Autowired
    private PlayerService playerService;

    public void evaluate(SportEventResult result, Wager wager) {
        BetType wagerBetType = wager.getOutcome().getBet().getBetType();
        switch (wagerBetType) {
        case WINNER:
            update(result.getWinner(), wager);
            break;
        case FINAL_SCORE:
            update(result.getFinalScore(), wager);
            break;
        default:
            throw new IllegalArgumentException("There is no rule to envaluate " + wager.getOutcome().getBet().getBetType() + " bet type.");
        }        
    }

    private void update(String description, Wager wager) {
        if (description.equals(wager.getOutcome().getDescription())) {
            wager.setProcessed(true);
            wager.setWin(true);
            wagerService.save(wager);
            updatePlayerBalance(wager.getPlayer(), wager.getOutcome().getValue(), wager.getAmount());
        } else {
            wager.setProcessed(true);
            wager.setWin(false);
            wagerService.save(wager);
        }
    }

    private void updatePlayerBalance(Player player, BigDecimal odd, BigDecimal amount) {
        BigDecimal prize = amount.multiply(odd);
        playerService.updateBalance(player, player.getBalance().add(prize));
    }

    
}
