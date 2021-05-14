package com.training.sportsbetting.view.player;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.training.sportsbetting.domain.Currency;
import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.service.player.PlayerService;
import com.training.sportsbetting.service.player.security.AuthenticationChecker;

@ControllerAdvice
public class RechargeBoundaryProvider {

    private static final BigDecimal HUF_RECHARGE_BOUNDARY = BigDecimal.valueOf(10000);
    private static final BigDecimal USD_RECHARGE_BOUNDARY = BigDecimal.valueOf(30);
    private static final BigDecimal EUR_RECHARGE_BOUNDARY = BigDecimal.valueOf(25);

    @Autowired
    private PlayerService playerService;
    @Autowired
    private AuthenticationChecker authenticationChecker;

    @ModelAttribute
    public void addRechargeBoundary(Model model) {
        if (authenticationChecker.isAuthenticated()) {
            String playerName = getPlayerName();
            Player player = playerService.findByUsername(playerName);
            model.addAttribute("rechargeBoundary", getRechargeBoundary(player.getCurrency()));
        }
    }

    private String getPlayerName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private BigDecimal getRechargeBoundary(Currency currency) {
        BigDecimal rechargeBoundary;
        switch (currency) {
        case HUF:
            rechargeBoundary = HUF_RECHARGE_BOUNDARY;
            break;
        case USD:
            rechargeBoundary = USD_RECHARGE_BOUNDARY;
            break;
        case EUR:
            rechargeBoundary = EUR_RECHARGE_BOUNDARY;
            break;
        default:
            throw new IllegalArgumentException("Does not have declared recharge boundary for " + currency + " currency type.");
        }
        return rechargeBoundary;
    }
}
