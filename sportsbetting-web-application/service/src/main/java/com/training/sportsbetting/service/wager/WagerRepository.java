package com.training.sportsbetting.service.wager;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.sportsbetting.domain.Outcome;
import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.domain.SportEvent;
import com.training.sportsbetting.domain.Wager;

@Repository
public interface WagerRepository extends JpaRepository<Wager, Long> {

    @Query(value = "SELECT w FROM Wager w WHERE w.processed is FALSE")
    public List<Wager> findActiveWagers();

    @Query(value = "SELECT COUNT(w) FROM Wager w WHERE w.outcome IN ?1")
    public Integer countWagers(Outcome outcome);

    @Query(value = "SELECT w FROM Wager w WHERE  w.player.username = ?1")
    public Page<Wager> findByPlayer(String username, Pageable pageable);

    @Query(value = "SELECT w FROM Wager w WHERE  w.player.username = ?1")
    public List<Wager> findByPlayer(String username);

    @Query(value = "SELECT w FROM Wager w WHERE  w.player = ?1")
    public List<Wager> findByPlayer(Player player);

    @Query(value = "SELECT w FROM Wager w WHERE w.outcome.bet.sportEvent = ?1")
    public List<Wager> findAllBySportEvent(SportEvent sportEvent);

    @Query(value = "SELECT w FROM Wager w WHERE w.processed = false AND w.outcome.bet.sportEvent.endTime < now()")
    public List<Wager> findAllUnprocessedWagerWhereSportEventHasBeenEnded();

}
