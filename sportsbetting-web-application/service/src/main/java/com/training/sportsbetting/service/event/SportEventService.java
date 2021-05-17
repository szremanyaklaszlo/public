package com.training.sportsbetting.service.event;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.Bet;
import com.training.sportsbetting.domain.Outcome;
import com.training.sportsbetting.domain.SportEvent;
import com.training.sportsbetting.service.event.exception.SportEventNotFoundException;

@Service("SportEventService")
public class SportEventService implements SportEventCrud, SportEventActiveSearch, OutcomesCollector<SportEvent> {

    @Autowired
    private SportEventRepository sportEventRepository;

    @Override
    public void save(SportEvent sportEvent) {
        Objects.requireNonNull(sportEvent, "Sport event must not be null.");
        sportEventRepository.save(sportEvent);
    }

    @Override
    public void saveAll(List<SportEvent> sportEvents) {
        Objects.requireNonNull(sportEvents, "Sport event list must not be null.");
        sportEvents.forEach(sportEvent -> Objects.requireNonNull(sportEvent, "Sport event must not be null."));
        sportEventRepository.saveAll(sportEvents);
    }

    @Override
    public SportEvent findById(Long id) {
        Objects.requireNonNull(id, "Id must not be null.");
        return sportEventRepository.findById(id)
                .orElseThrow(() -> new SportEventNotFoundException("Sport event has not been found by id=" + id + "."));
    }

    @Override
    public List<SportEvent> findAll() {
        return sportEventRepository.findAll();
    }

    @Override
    public List<SportEvent> findActiveSportEvents() {
        return sportEventRepository.findActiveSportEvents();
    }

    @Override
    public Page<SportEvent> findActiveSportEvents(Pageable pageable) {
        Objects.requireNonNull(pageable, "Pageable must not be null!");
        return sportEventRepository.findActiveSportEvents(pageable);
    }

    @Override
    public Page<SportEvent> findActiveSportEventsByFilter(String tableSearchFilter, Pageable pageable) {
        Objects.requireNonNull(pageable, "Pageable must not be null!");
        Objects.requireNonNull(tableSearchFilter, "Table filter must not be null!");
        return sportEventRepository.findActiveSportEventsByFilter(tableSearchFilter, pageable);
    }

    @Override
    public void deleteAll() {
        sportEventRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        Objects.requireNonNull(id, "Id must not be null.");
        if (!sportEventRepository.existsById(id)) {
            throw new SportEventNotFoundException("Sport event has not been found by id " + id + ".");
        }
        sportEventRepository.deleteById(id);
    }

    @Override
    public List<Outcome> collectOutcomes(SportEvent sportEvent) {
        Objects.requireNonNull(sportEvent, "Sport event must not be null.");
        return sportEvent.getBets()
                .stream()
                .map(Bet::getOutcomes)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Outcome> collectOutcomes(List<SportEvent> sportEvents) {
        return sportEvents.stream()
                .map(SportEvent::getBets)
                .flatMap(List::stream)
                .map(Bet::getOutcomes)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
