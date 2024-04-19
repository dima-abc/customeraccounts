package com.testone.customeraccounts.validator;

import com.testone.customeraccounts.controller.payload.NewAccountPayload;
import com.testone.customeraccounts.entity.Platform;
import com.testone.customeraccounts.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class PayloadValidator {
    private final PlatformService platformService;

    public Platform getPlatform(String header) {
        return platformService.findPlatformByName(header)
                .orElseThrow(() -> new NoSuchElementException("customer.errors.platform.not_found"));
    }

    public BindingResult isValid(String header, NewAccountPayload payload) {
        final Platform platform = getPlatform(header);
        final AccountValidator validator = new AccountValidator(platform);
        final DataBinder dataBinder = new DataBinder(payload);
        dataBinder.addValidators(validator);
        dataBinder.validate();
        return dataBinder.getBindingResult();
    }
}
