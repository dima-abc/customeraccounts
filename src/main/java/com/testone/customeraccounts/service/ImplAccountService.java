package com.testone.customeraccounts.service;

import com.testone.customeraccounts.entity.Account;
import com.testone.customeraccounts.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImplAccountService implements AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Iterable<Account> findAccountByAccountParam(Map<String, String> findAccountParam) {
        return accountRepository
                .findAccountByLastNameOrFirstNameOrMiddleNameOrPhoneOrEmail(
                        findAccountParam.get("lastName"),
                        findAccountParam.get("firstName"),
                        findAccountParam.get("middleName"),
                        findAccountParam.get("phone"),
                        findAccountParam.get("email"));
    }
}
