package com.training.sportsbetting.view.events.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.sportsbetting.domain.SportEvent;
import com.training.sportsbetting.service.event.SportEventCrud;

@RestController
@RequestMapping("/api/sportEvent")
public class SportEventUploadController {

    @Autowired
    @Qualifier("SportEventService")
    private SportEventCrud sportEventService;
    
    @GetMapping("/all")
    public List<SportEvent> findAll() {
        return sportEventService.findAll();
    }
    
    @PostMapping("/saveAll")
    public HttpStatus saveAll(@RequestBody List<SportEvent> sportEvents) {
        sportEventService.saveAll(sportEvents);
        return HttpStatus.CREATED;
    }
}
