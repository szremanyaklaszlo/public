package com.training.sportsbetting.view.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.service.player.PlayerService;
import com.training.sportsbetting.service.player.security.AuthenticationChecker;
import com.training.sportsbetting.view.player.model.PlayerModelConverter;

@ControllerAdvice
public class PlayerModelProvider {

    @Autowired
    private AuthenticationChecker authenticationChecker;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerModelConverter playerModelConverter;

    @ModelAttribute
    public void addPlayerModel(Model model) {
        if (authenticationChecker.isAuthenticated()) {
            String playerName = getPlayerName();
            Player player = playerService.findByUsername(playerName);
            model.addAttribute("playerModel", playerModelConverter.toModel(player));
        }
    }

    private String getPlayerName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
