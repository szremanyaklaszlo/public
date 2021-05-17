package com.training.sportsbetting.view.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.sportsbetting.domain.SportEvent;
import com.training.sportsbetting.service.event.SportEventActiveSearch;
import com.training.sportsbetting.service.player.security.AuthenticationChecker;
import com.training.sportsbetting.view.events.model.SportEventModel;
import com.training.sportsbetting.view.events.model.SportEventModelConverter;

@Controller
public class EventsController {

    @Autowired
    @Qualifier("SportEventService")
    private SportEventActiveSearch sportEventService;
    @Autowired
    private SportEventModelConverter modelConverter;
    @Autowired
    private AuthenticationChecker authenticationChecker;

    @GetMapping("/events")
    public String getEventsPage() {
        String page;
        if (authenticationChecker.isAuthenticated()) {
            page = "events-user";
        } else {
            page = "events";
        }
        return page;
    }

    @PostMapping("search")
    public String search(
            @ModelAttribute("tableSearchFilter") String tableSearchFilter,
            @ModelAttribute("page") int page,
            @ModelAttribute("pageSize") int pageSize,
            @ModelAttribute("sortField") String sortField,
            @ModelAttribute("sortDirection") String sortDirection) {
        return "redirect:events?"
                + "page=" + page
                + "&pageSize=" + pageSize
                + "&sortField=" + sortField
                + "&sortDirection=" + sortDirection
                + "&tableSearchFilter=" + tableSearchFilter;
    }

    @ModelAttribute("events")
    public List<SportEventModel> getSportEvents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "startTime") String sortField,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "") String tableSearchFilter,
            Model model) {
        Pageable pageable = getPagable(page, pageSize, sortField, sortDirection);
        Page<SportEvent> sportEvents = findActiveSportEvents(tableSearchFilter, pageable);
        page = correctingPageNumber(sportEvents);
        addPagingAttributes(model, page, pageSize, sportEvents.getTotalPages());
        addSortingAttributes(model, sortField, sortDirection);
        addSearchFilterAttributes(model, tableSearchFilter);
        return modelConverter.toModel(sportEvents.getContent());
    }

    private int correctingPageNumber(Page<SportEvent> sportEvents) {
        return sportEvents.getNumber() + 1;
    }

    private Page<SportEvent> findActiveSportEvents(String tableSearchFilter, Pageable pageable) {
        Page<SportEvent> sportEvents = sportEventService.findActiveSportEventsByFilter(tableSearchFilter, pageable);
        if (sportEvents.getContent().isEmpty()) {
            pageable = sportEvents.previousOrFirstPageable();
            sportEvents = sportEventService.findActiveSportEventsByFilter(tableSearchFilter, pageable);
        }
        return sportEvents;
    }

    private Pageable getPagable(int page, int pageSize, String sortField, String sortDirection) {
        Sort sort = Sort.by(Direction.valueOf(sortDirection), sortField);
        return PageRequest.of(page - 1, pageSize, sort);
    }

    private void addPagingAttributes(Model model, int page, int pageSize, int totalPages) {
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
    }

    private void addSortingAttributes(Model model, String sortField, String sortDirection) {
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
    }

    private void addSearchFilterAttributes(Model model, String tableSearchFilter) {
        model.addAttribute("tableSearchFilter", tableSearchFilter);
    }
}
