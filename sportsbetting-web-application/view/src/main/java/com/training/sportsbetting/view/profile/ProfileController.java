package com.training.sportsbetting.view.profile;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.service.player.PlayerService;
import com.training.sportsbetting.service.player.security.AuthenticationChecker;

@Controller
public class ProfileController {

    @Autowired
    private AuthenticationChecker authenticationChecker;
    @Autowired
    private PlayerService playerService;
    
    @GetMapping(value = "/profile")
    public String getProfile() {
        return authenticationChecker.isAuthenticated() ? "profile-user" : HttpStatus.FORBIDDEN.toString();
    }
    
    @ModelAttribute("ingameMoney")
    public BigDecimal getIngameMoney() {
        String username = getPlayerName();
        Player player = playerService.findByUsername(username);
        BigDecimal ingameMoney = playerService.getIngameMoney(player);
        return ingameMoney;
    }
    
    
    private String getPlayerName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
