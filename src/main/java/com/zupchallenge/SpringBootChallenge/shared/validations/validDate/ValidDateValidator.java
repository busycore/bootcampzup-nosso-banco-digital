package com.zupchallenge.SpringBootChallenge.shared.validations.validDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class ValidDateValidator implements ConstraintValidator<ValidDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate receivedDate, ConstraintValidatorContext context) {

        if(receivedDate == null){
            return false;
        }

        LocalDate Today = LocalDate.now();
        Period period = Period.between(receivedDate,Today);
        return period.getYears()>=18;

    }

    @Override
    public void initialize(ValidDate constraintAnnotation) {

    }
}
