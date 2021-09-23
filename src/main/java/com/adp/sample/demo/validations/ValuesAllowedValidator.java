package com.adp.sample.demo.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValuesAllowedValidator implements ConstraintValidator<ValuesAllowed, Integer> {

    private List<Integer> expectedValues;
    private String returnMessage;

    @Override
    public void initialize(ValuesAllowed requiredIfChecked) {
        expectedValues = Arrays.stream(requiredIfChecked.values()).boxed().collect(Collectors.toList());
        returnMessage = requiredIfChecked.message().concat(expectedValues.toString());
    }

    @Override
    public boolean isValid(Integer testValue, ConstraintValidatorContext context) {
        boolean valid = expectedValues.contains(testValue);

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(returnMessage)
                    .addConstraintViolation();
        }
        return valid;
    }
}
