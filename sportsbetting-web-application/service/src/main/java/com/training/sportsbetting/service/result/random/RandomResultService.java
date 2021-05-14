package com.training.sportsbetting.service.result.random;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.SportEvent;
import com.training.sportsbetting.domain.Wager;
import com.training.sportsbetting.service.result.ResultGenerator;
import com.training.sportsbetting.service.result.ResultService;
import com.training.sportsbetting.service.result.SportEventResult;
import com.training.sportsbetting.service.result.WagerEvaluator;
import com.training.sportsbetting.service.wager.WagerService;

@Service("RandomResultService")
public class RandomResultService implements ResultService {

    private final static Logger LOG = LoggerFactory.getLogger(RandomResultService.class);
    
    @Autowired
    private WagerService wagerService;
    @Autowired
    private WagerEvaluator wagerEvaluator;
    @Autowired
    @Qualifier("RandomResultGenerator")
    private ResultGenerator resultGenerator;

    @Override
    public void run() {
        while (true) {
            try {
                updateWagers();
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                LOG.error("ResultService has been interrupted.");
            }
        }

    }

    @Override
    public synchronized void updateWagers() {
        List<Wager> wagers = getWagersToBeProcessed();
        Map<SportEvent, List<Wager>> wagersBySportEvents = groupBySportEvents(wagers);
        wagersBySportEvents.entrySet().stream()
                .forEach(map -> evaluateWagers(map.getValue(), resultGenerator.generateResult(map.getKey())));
    }

    private List<Wager> getWagersToBeProcessed() {
        return wagerService.findAllUnprocessedWagerWhereSportEventHasBeenEnded();
    }

    private Map<SportEvent, List<Wager>> groupBySportEvents(List<Wager> wagers) {
        return wagers.stream().collect(Collectors.groupingBy(wager -> wager.getOutcome().getBet().getSportEvent()));
    }

    private void evaluateWagers(List<Wager> wagers, SportEventResult result) {
        for (Wager wager : wagers) {
            wagerEvaluator.evaluate(result, wager);
        }
    }

}
