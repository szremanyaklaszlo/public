package com.training.sportsbetting.view.registration;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.training.sportsbetting.domain.Player;
import com.training.sportsbetting.service.player.PlayerBuilder;
import com.training.sportsbetting.service.player.PlayerService;
import com.training.sportsbetting.view.registration.model.RegistrationModel;

@Controller
public class RegistrationController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerBuilder playerBuilder;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        return "registration";
    }

    @ModelAttribute("registrationModel")
    public RegistrationModel addRegistrationModel() {
        return new RegistrationModel();
    }

    @PostMapping("save_player")
    public String savePlayer(@Valid RegistrationModel registrationModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "registration";
        }
        Player player = createPlayer(registrationModel);
        playerService.savePlayer(player);
        return "redirect:login";
    }

    private Player createPlayer(RegistrationModel registrationModel) {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        return playerBuilder
                .setFirstName(registrationModel.getFirstName())
                .setLastName(registrationModel.getLastName())
                .setUsername(registrationModel.getUsername())
                .setPassword(passwordEncoder.encode(registrationModel.getPassword()))
                .setEmail(registrationModel.getEmail())
                .setCurrency(registrationModel.getCurrency())
                .setBirth(registrationModel.getBirth())
                .setGrantedAuthority(grantedAuthority)
                .build();
    }

}
