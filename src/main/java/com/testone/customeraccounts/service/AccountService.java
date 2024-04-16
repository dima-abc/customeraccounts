package com.testone.customeraccounts.service;

import com.testone.customeraccounts.entity.Account;
import com.testone.customeraccounts.service.model.FindAccountParam;

import java.util.Optional;

public interface AccountService {

    Account createAccount(Account account);

    Optional<Account> findAccountById(Long id);

    Iterable<Account> findAccountByAccountParam(FindAccountParam findAccountParam);
}
