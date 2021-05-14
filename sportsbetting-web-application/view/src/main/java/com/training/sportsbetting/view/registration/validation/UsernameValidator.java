package com.training.sportsbetting.view.registration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.sportsbetting.service.player.PlayerService;

public class UsernameValidator implements ConstraintValidator<UsernameConstraint, String>{

    private final static int MIN_LENGTH = 2;
    private final static int MAX_LENGTH = 20;
    
    @Autowired
    private PlayerService playerService;
    
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return isNotNull(username) && moreThen(username, MIN_LENGTH) && lessThen(username, MAX_LENGTH) && !isReserved(username);
    }

    private boolean isNotNull(String username) {
        return username != null;
    }

    private boolean moreThen(String username, int minLenght) {
        return username.length() > minLenght;
    }

    private boolean lessThen(String username, int maxLenght) {
        return username.length() < maxLenght;
    }

    private boolean isReserved(String username) {
        return playerService.isReservedUsername(username);
    }

}
