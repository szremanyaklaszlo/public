package com.training.sportsbetting.view.registration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.sportsbetting.service.player.PlayerService;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {

    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";
    
    @Autowired
    private PlayerService playerService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return isNotNull(email) && isWellFormed(email) && !isReserved(email);
    }

    private boolean isNotNull(String email) {
        return email != null;
    }

    private boolean isWellFormed(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    private boolean isReserved(String email) {
        return playerService.isReservedEmail(email);
    }

}
