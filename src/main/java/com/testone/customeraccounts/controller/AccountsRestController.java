package com.testone.customeraccounts.controller;

import com.testone.customeraccounts.controller.payload.NewAccountPayload;
import com.testone.customeraccounts.entity.Account;
import com.testone.customeraccounts.service.AccountService;
import com.testone.customeraccounts.service.mapper.AccountMapper;
import com.testone.customeraccounts.validator.PayloadValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("customer-api/accounts")
@RequiredArgsConstructor
public class AccountsRestController {
    private final AccountService accountService;
    private final PayloadValidator payloadValidator;

    private final String X_SOURCE = "x-Source";

    @GetMapping()
    public Iterable<Account> findAccountByAccountParam(@RequestParam(required = false) Map<String, String> accountParam) {
        if (accountParam.isEmpty()) {
            throw new NoSuchElementException("customer.errors.account.find_param");
        }
        return accountService.findAccountByAccountParam(accountParam);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(@RequestHeader(X_SOURCE) String headerValue,
                                           @RequestBody NewAccountPayload newAccountPayload,
                                           UriComponentsBuilder uriComponentsBuilder) throws BindException {
        BindingResult bindingResult = payloadValidator.isValid(headerValue, newAccountPayload);
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Account accountNew = AccountMapper.mapToAccount(newAccountPayload);
            Account account = this.accountService.createAccount(accountNew);
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/customer-api/accounts/{accountId}")
                            .build(Map.of("accountId", account.getId())))
                    .body(account);
        }
    }
}
