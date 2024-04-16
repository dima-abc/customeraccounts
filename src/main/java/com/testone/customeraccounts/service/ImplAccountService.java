package com.testone.customeraccounts.service;

import com.testone.customeraccounts.entity.Account;
import com.testone.customeraccounts.repository.AccountRepository;
import com.testone.customeraccounts.service.model.FindAccountParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Iterable<Account> findAccountByAccountParam(FindAccountParam findAccountParam) {
        return accountRepository
                .findAccountByLastNameOrFirstNameOrMiddleNameOrPhoneOrEmail(
                        findAccountParam.getLastName(),
                        findAccountParam.getFirstName(),
                        findAccountParam.getMiddleName(),
                        findAccountParam.getPhone(),
                        findAccountParam.getEmail());
    }
}
