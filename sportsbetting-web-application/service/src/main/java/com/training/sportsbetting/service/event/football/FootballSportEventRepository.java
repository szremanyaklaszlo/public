package com.training.sportsbetting.service.event.football;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.sportsbetting.domain.FootballSportEvent;

@Repository
interface FootballSportEventRepository extends JpaRepository<FootballSportEvent, Long> {

}
