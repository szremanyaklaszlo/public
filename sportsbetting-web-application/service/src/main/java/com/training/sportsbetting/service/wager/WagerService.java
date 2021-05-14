package com.training.sportsbetting.service.wager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.Outcome;
import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.domain.SportEvent;
import com.training.sportsbetting.domain.Wager;
import com.training.sportsbetting.service.event.OutcomesCollector;
import com.training.sportsbetting.service.player.PlayerService;

@Service
public class WagerService {

    @Autowired
    private WagerRepository wagerRepository;
    @Autowired
    @Qualifier("SportEventService")
    private OutcomesCollector<SportEvent> sportEventService;
    @Autowired
    private PlayerService playerService;

    public List<Wager> findAll() {
        return wagerRepository.findAll();
    }

    public List<Wager> findAllByPlayer(String username) {
        Objects.requireNonNull(username, "Username must not be null.");
        return wagerRepository.findByPlayer(username);
    }

    public Page<Wager> findAllByPlayer(String username, Pageable pageable) {
        Objects.requireNonNull(username, "Username must not be null.");
        Objects.requireNonNull(pageable, "Pageable must not be null!");
        return wagerRepository.findByPlayer(username, pageable);
    }

    public List<Outcome> collectWagerOutcomes(List<Wager> wagers) {
        Objects.requireNonNull(wagers, "Wager list must not be null.");
        return wagers.stream()
                .map(Wager::getOutcome)
                .collect(Collectors.toList());
    }

    public Integer countWagers(SportEvent sportEvent) {
        List<Outcome> outcomes = sportEventService.collectOutcomes(sportEvent);
        return outcomes.stream()
                .mapToInt(outcome -> wagerRepository.countWagers(outcome))
                .sum();
    }

    public void save(Outcome outcome, Player player, BigDecimal amount) {
        if (outcome == null || player == null || amount == null) {
            throw new NullPointerException("Either outcome, player and amount must not be null.");
        }
        Wager wager = new Wager(amount, LocalDateTime.now(), LocalDateTime.now().plusYears(5), outcome, player, player.getCurrency());
        playerService.updateBalance(player, player.getBalance().subtract(amount));
        wagerRepository.save(wager);
    }

    public List<Wager> findAllByPlayer(Player player) {
        Objects.requireNonNull(player, "Player list must not be null.");
        return wagerRepository.findByPlayer(player);
    }

    public List<Wager> findAllUnprocessedWagerWhereSportEventHasBeenEnded() {
        return wagerRepository.findAllUnprocessedWagerWhereSportEventHasBeenEnded();
    }

    public void save(Wager wager) {
        wagerRepository.save(wager);
    }

}
