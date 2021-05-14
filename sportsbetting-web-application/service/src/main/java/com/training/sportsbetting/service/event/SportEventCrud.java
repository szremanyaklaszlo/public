package com.training.sportsbetting.service.event;

import java.util.List;

import com.training.sportsbetting.domain.SportEvent;

public interface SportEventCrud {
    
    void save(SportEvent sportEvent);
    void saveAll(List<SportEvent> sportEvents);
    SportEvent findById(Long id);
    List<SportEvent> findAll();
    void deleteById(Long id);
    void deleteAll();
    
}
