package com.training.sportsbetting.view.player.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.Player;

@Service
public class PlayerModelConverter {

    @Autowired
    private PlayerModelBuilder playerModelBuilder;

    public PlayerModel toModel(Player player) {
        return playerModelBuilder
                .setId(player.getId())
                .setFirstName(player.getFirstName())
                .setLastName(player.getLastName())
                .setUsername(player.getUsername())
                .setEmail(player.getEmail())
                .setCurrency(player.getCurrency())
                .setBalance(player.getBalance())
                .setBirth(player.getBirth())
                .build();
    }
}
