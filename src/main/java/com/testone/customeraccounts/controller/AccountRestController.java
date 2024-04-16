package com.testone.customeraccounts.controller;

import com.testone.customeraccounts.entity.Account;
import com.testone.customeraccounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("customer-api/accounts/{accountId:\\d+}")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @ModelAttribute("account")
    public Account getAccount(@PathVariable("accountId") long accountId) {
        return this.accountService.findAccountById(accountId)
                .orElseThrow(() -> new NoSuchElementException("Учетная запись не найдена"));
    }

    @GetMapping
    public Account findAccount(@ModelAttribute("account") Account account) {
        return account;
    }
}
