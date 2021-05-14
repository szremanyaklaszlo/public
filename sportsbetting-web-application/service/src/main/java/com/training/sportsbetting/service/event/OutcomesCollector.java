package com.training.sportsbetting.service.event;

import java.util.List;

import com.training.sportsbetting.domain.Outcome;
import com.training.sportsbetting.domain.SportEvent;

public interface OutcomesCollector <T extends SportEvent>{

    List<Outcome> collectOutcomes(T t);
    List<Outcome> collectOutcomes(List<T> t);
}
