package com.training.sportsbetting.view.registration.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthDateValidator implements ConstraintValidator<BirthDateConstraint, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        return isNotNull(birthDate) && isOverEighteen(birthDate);
    }

    private boolean isNotNull(LocalDate birthDate) {
        return birthDate != null;
    }

    private boolean isOverEighteen(LocalDate birthDate) {
        return birthDate.compareTo(LocalDate.now().minusYears(18)) <= 0;
    }

}
