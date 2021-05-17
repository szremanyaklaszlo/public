package com.training.sportsbetting.api.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.sportsbetting.domain.FootballSportEvent;
import com.training.sportsbetting.service.event.football.FootballSportEventService;

@RestController
@RequestMapping("/api/event/football")
public class FootballSportEventRestController {

    @Autowired
    private FootballSportEventService footballService;

    @PostMapping(value = "upload", consumes = "application/json")
    public HttpStatus saveFootballEvents(@RequestBody List<FootballSportEvent> footballEvents) {
        footballService.saveAll(footballEvents);
        return HttpStatus.CREATED;
    }
    
    @GetMapping(value = "get/all", produces = "application/json")
    public ResponseEntity<List<FootballSportEvent>> getAllEvent() {
        return ResponseEntity.ok(footballService.findAll());
    }

}
