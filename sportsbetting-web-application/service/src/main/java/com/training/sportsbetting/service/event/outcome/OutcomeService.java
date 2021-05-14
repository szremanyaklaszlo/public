package com.training.sportsbetting.service.event.outcome;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.Outcome;
import com.training.sportsbetting.service.event.outcome.exception.OutcomeNotFoundException;

@Service
public class OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;

    public List<Outcome> findAll() {
        return outcomeRepository.findAll();
    }

    public Outcome findById(Long outcomeId) {
        return outcomeRepository.findById(outcomeId)
                .orElseThrow(() -> new OutcomeNotFoundException("Outcome has not been found by id " + outcomeId + "."));
    }

}
