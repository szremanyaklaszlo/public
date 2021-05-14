package com.training.sportsbetting.service.player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.domain.Wager;
import com.training.sportsbetting.service.player.exception.UserNotFoundException;
import com.training.sportsbetting.service.wager.WagerService;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private WagerService wagerService;

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public Player findById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new UserNotFoundException("Player has not been found by id " + playerId + "."));
    }

    public Player findByUsername(String username) {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Player has not been found by username " + username + "."));
    }
    
    public Player findByEmail(String email) {
        return playerRepository.findByUsername(email)
                .orElseThrow(() -> new UserNotFoundException("Player has not been found by email " + email + "."));
    }

    public boolean isReservedUsername(String username) {
        Optional<Player> player = playerRepository.findByUsername(username);
        return player.isPresent();
    }

    public boolean isReservedEmail(String email) {
        Optional<Player> player = playerRepository.findByEmail(email);
        return player.isPresent();
    }

    public synchronized void updateBalance(Player player, BigDecimal amount) {
        if (isNegative(amount)) {
            throw new IllegalStateException("Player balance cannot fall below zero.");
        }
        player.setBalance(amount);
        playerRepository.save(player);
    }

    private boolean isNegative(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == -1;
    }

    public BigDecimal getIngameMoney(Player player) {
        List<Wager> wagers = wagerService.findAllByPlayer(player);
        BigDecimal ingameMoney = countIngameMoney(wagers);
        return ingameMoney;
    }

    private BigDecimal countIngameMoney(List<Wager> wagers) {
        return wagers.stream()
                .filter(wager -> wager.isProcessed() == false)
                .map(wager -> wager.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
