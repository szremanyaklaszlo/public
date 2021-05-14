package com.training.sportsbetting.view.bet;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.sportsbetting.domain.Outcome;
import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.domain.SportEvent;
import com.training.sportsbetting.service.event.SportEventService;
import com.training.sportsbetting.service.event.outcome.OutcomeService;
import com.training.sportsbetting.service.player.PlayerService;
import com.training.sportsbetting.service.wager.WagerService;
import com.training.sportsbetting.view.bet.model.OutcomeModel;
import com.training.sportsbetting.view.bet.model.OutcomeModelConverter;
import com.training.sportsbetting.view.bet.model.WagerTicket;

@Controller
public class BetController {

    @Autowired
    private SportEventService sportEventService;
    @Autowired
    private WagerService wagerService;
    @Autowired
    PlayerService playerService;
    @Autowired
    private OutcomeService outcomeService;
    @Autowired
    private OutcomeModelConverter modelConverter;

    @GetMapping("/bet")
    public ModelAndView getBettingPage(
            @RequestParam Long sportEventId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "type") String sortField,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        ModelAndView modelAndView = new ModelAndView("bet-user");
        PagedListHolder<Outcome> outcomes = getOutcomes(sportEventId, page, pageSize, sortField, sortDirection);
        List<OutcomeModel> outcomeModels = modelConverter.toModel(outcomes.getPageList());
        page = correctingPageNumber(page, outcomes.getPageCount());
        addModelAttributesForTable(modelAndView, sportEventId, page, pageSize, sortField, sortDirection, outcomes.getPageCount(), outcomeModels);
        return modelAndView;
    }

    private PagedListHolder<Outcome> getOutcomes(Long sportEventId, int page, int pageSize, String sortField, String sortDirection) {
        SportEvent sportEvent = sportEventService.findById(sportEventId);
        List<Outcome> oucomes = sportEventService.collectOutcomes(sportEvent);
        PagedListHolder<Outcome> pagedOutcomes = new PagedListHolder<Outcome>(oucomes);
        pagedOutcomes.setPage(page - 1);
        pagedOutcomes.setPageSize(pageSize);
        return pagedOutcomes;
    }

    private int correctingPageNumber(int page, int lastPage) {
        return page < lastPage ? page : lastPage;
    }

    private void addModelAttributesForTable(ModelAndView modelAndView, Long sportEventId, int page, int pageSize, String sortField, String sortDirection,
            int lastPage, List<OutcomeModel> outcomeModels) {
        modelAndView.addObject("outcomes", outcomeModels);
        modelAndView.addObject("sportEventId", sportEventId);
        modelAndView.addObject("page", page);
        modelAndView.addObject("totalPages", lastPage);
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject("sortField", sortField);
        modelAndView.addObject("sortDirection", sortDirection);
    }

    @ModelAttribute("wagerTicket")
    public WagerTicket addWagerTicket() {
        return new WagerTicket();
    }

    @PostMapping("save_wager")
    public String saveWager(
            @ModelAttribute WagerTicket wagerTicket,
            @RequestParam Long sportEventId,
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam String sortField,
            @RequestParam String sortDirection,
            RedirectAttributes redirectAttributes) {
        Outcome outcome = outcomeService.findById(wagerTicket.getOutcomeId());
        Player player = playerService.findById(wagerTicket.getPlayerId());
        BigDecimal amount = wagerTicket.getAmount();
        saveWager(redirectAttributes, outcome, player, amount);
        String url = "redirect:/bet?"
                + "sportEventId=" + sportEventId
                + "&page=" + page
                + "&pageSize=" + pageSize
                + "&sortField=" + sortField
                + "&sortDirection=" + sortDirection;
        return url;
    }

    private void saveWager(RedirectAttributes redirectAttributes, Outcome outcome, Player player, BigDecimal amount) {
        if (amount != null && player.getBalance() != null && amount.compareTo(BigDecimal.ZERO) == 1 && (player.getBalance().compareTo(amount) == 0
                || player.getBalance().compareTo(amount) == 1)) {
            redirectAttributes.addFlashAttribute("wagerSuccessMessage", "Successfully processed!");
            wagerService.save(outcome, player, amount);
        } else {
            redirectAttributes.addFlashAttribute("wagerFailedMessage", "Invalid amount!");
        }
    }

}
