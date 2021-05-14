package com.training.sportsbetting.service.result;

import com.training.sportsbetting.domain.SportEvent;

public interface ResultGenerator {

    SportEventResult generateResult(SportEvent sportEvent);
}
