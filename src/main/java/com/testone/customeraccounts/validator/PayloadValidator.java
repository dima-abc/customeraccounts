package com.testone.customeraccounts.validator;

import com.testone.customeraccounts.controller.payload.NewAccountPayload;
import jakarta.validation.*;
import org.hibernate.validator.HibernateValidator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
public class PayloadValidator {

    public void isValid(String header, NewAccountPayload payload) {
        Class<?> validGroup = Arrays.stream(HeaderValidGroup.class.getClasses())
                .filter(c -> c.getSimpleName().equalsIgnoreCase(header))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Заголовок %s не найден".formatted(header)));
        Set<ConstraintViolation<NewAccountPayload>> violations = getValidator().validate(payload, validGroup);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
