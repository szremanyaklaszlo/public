package com.training.sportsbetting.api.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.sportsbetting.domain.SportEvent;
import com.training.sportsbetting.service.event.SportEventCrud;

@RestController
@RequestMapping("/api/event")
public class SportEventRestController {

    @Autowired
    @Qualifier("SportEventService")
    private SportEventCrud SportEventCrud;

    @GetMapping(value = "get/all", produces = "application/json")
    public ResponseEntity<List<SportEvent>> getAllEvent() {
        return ResponseEntity.ok(SportEventCrud.findAll());
    }

    @DeleteMapping(value = "delete/{id}")
    public HttpStatus deleteSportEvent(@PathVariable Long id) {
        SportEventCrud.deleteById(id);
        return HttpStatus.OK;
    }
    
    @DeleteMapping(value = "delete/all")
    public HttpStatus deleteAllEvent() {
        SportEventCrud.deleteAll();
        return HttpStatus.OK;
    }
}
