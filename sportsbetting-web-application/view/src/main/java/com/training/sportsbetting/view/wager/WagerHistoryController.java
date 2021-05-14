package com.training.sportsbetting.view.wager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.sportsbetting.domain.Wager;
import com.training.sportsbetting.service.wager.WagerService;
import com.training.sportsbetting.view.wager.model.WagerModel;
import com.training.sportsbetting.view.wager.model.WagerModelConverter;

@Controller
public class WagerHistoryController {

    @Autowired
    private WagerModelConverter wagerModelConverter;
    @Autowired
    private WagerService wagerService;

    @GetMapping("/history")
    public String getHistory() {
        return "history-user";
    }

    @ModelAttribute("wagers")
    public List<WagerModel> getSportEvents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "validFrom") String sortField,
            @RequestParam(defaultValue = "DESC") String sortDirection,
            Model model) {
        Pageable pageable = getPagable(page, pageSize, sortField, sortDirection);
        String username = getPlayerName();
        Page<Wager> wagers = findAllWagerByPlayer(pageable, username);
        page = correctingPageNumber(wagers);
        addPagingAttributes(model, page, pageSize, wagers.getTotalPages());
        addSortingAttributes(model, sortField, sortDirection);
        return wagerModelConverter.toModel(wagers.getContent());
    }

    private Page<Wager> findAllWagerByPlayer(Pageable pageable, String username) {
        Page<Wager> wagers = wagerService.findAllByPlayer(username, pageable);
        if (wagers.getContent().isEmpty()) {
            pageable = wagers.previousOrFirstPageable();
            wagers = wagerService.findAllByPlayer(username, pageable);
        }
        return wagers;
    }

    private Pageable getPagable(int page, int pageSize, String sortField, String sortDirection) {
        Sort sort = Sort.by(Direction.valueOf(sortDirection), sortField);
        return PageRequest.of(page - 1, pageSize, sort);
    }

    private String getPlayerName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private int correctingPageNumber(Page<Wager> wagers) {
        return wagers.getNumber() + 1;
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

}
