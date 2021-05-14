package com.training.sportsbetting.view.player;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.training.sportsbetting.domain.Currency;
import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.service.player.PlayerService;
import com.training.sportsbetting.service.player.security.AuthenticationChecker;

@Controller
public class RechargeController {

    private static final BigDecimal HUF_RECHARGE_AMOUNT = BigDecimal.valueOf(100000);
    private static final BigDecimal USD_RECHARGE_AMOUNT = BigDecimal.valueOf(300);
    private static final BigDecimal EUR_RECHARGE_AMOUNT = BigDecimal.valueOf(250);

    @Autowired
    private PlayerService playerService;
    @Autowired
    private AuthenticationChecker authenticationChecker;

    @PostMapping("recharge")
    public String rechargeBalance(HttpServletRequest request) {
        if (authenticationChecker.isAuthenticated()) {
            String playerName = getPlayerName();
            Player player = playerService.findByUsername(playerName);
            BigDecimal amount = getRechargeAmount(player.getCurrency());
            playerService.updateBalance(player, amount);
        }
        String previousUrl = request.getHeader("Referer");
        return "redirect:" + previousUrl;
    }

    private String getPlayerName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private BigDecimal getRechargeAmount(Currency currency) {
        BigDecimal rechargeAmount;
        switch (currency) {
        case HUF:
            rechargeAmount = HUF_RECHARGE_AMOUNT;
            break;
        case USD:
            rechargeAmount = USD_RECHARGE_AMOUNT;
            break;
        case EUR:
            rechargeAmount = EUR_RECHARGE_AMOUNT;
            break;
        default:
            throw new IllegalArgumentException("Does not have declared recharge amount for " + currency + " currency type.");
        }
        return rechargeAmount;
    }

}
