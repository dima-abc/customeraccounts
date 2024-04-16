package com.testone.customeraccounts.service;

import com.testone.customeraccounts.entity.Account;

import java.util.Map;
import java.util.Optional;

public interface AccountService {

    Account createAccount(Account account);

    Optional<Account> findAccountById(Long id);

    Iterable<Account> findAccountByAccountParam(Map<String, String> findAccountParam);
}
