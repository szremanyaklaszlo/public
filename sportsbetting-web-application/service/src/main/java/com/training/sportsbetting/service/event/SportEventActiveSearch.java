package com.training.sportsbetting.service.event;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.training.sportsbetting.domain.SportEvent;

public interface SportEventActiveSearch {
    
    List<SportEvent> findActiveSportEvents();
    Page<SportEvent> findActiveSportEvents(Pageable pageable);
    Page<SportEvent> findActiveSportEventsByFilter(String tableSearchFilter, Pageable pageable);

}
