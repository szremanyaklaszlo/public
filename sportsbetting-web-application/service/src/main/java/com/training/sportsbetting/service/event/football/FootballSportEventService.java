package com.training.sportsbetting.service.event.football;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.FootballSportEvent;

@Service
public class FootballSportEventService {

    @Autowired
    private FootballSportEventRepository sportEventRepository;
    
    public void saveAll(List<FootballSportEvent> sportEvent) {
        sportEventRepository.saveAll(sportEvent);
    }

    public List<FootballSportEvent> findAll() {
        return sportEventRepository.findAll();
    }
}
