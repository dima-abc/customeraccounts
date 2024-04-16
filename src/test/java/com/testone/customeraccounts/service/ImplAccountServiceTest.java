package com.testone.customeraccounts.service;

import com.testone.customeraccounts.entity.Account;
import com.testone.customeraccounts.repository.AccountRepository;
import com.testone.customeraccounts.service.model.FindAccountParam;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAccountServiceTest {
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    ImplAccountService service;

    Long id = 1L;
    Long bankId = 123456L;
    String lastName = "Петров";
    String firstName = "Никоглай";
    String middleName = "Викторович";
    LocalDate birthDate = LocalDate.of(1987, 02, 24);
    String passport = "1111 111111";
    String placeBirth = "г.Грозный";
    String phone = "79060050050";
    String email = "petrov@mail.ru";
    String addressRegistered = "г.Кемерово";
    String addressLife = "г.Минск";

    @Test
    void createAccountThenReturnCreateAccount() {
        doReturn(new Account(id, bankId, lastName, firstName,
                middleName, birthDate, passport, placeBirth,
                phone, email, addressRegistered, addressLife))
                .when(accountRepository).save(new Account(null, bankId, lastName, firstName,
                        middleName, birthDate, passport, placeBirth,
                        phone, email, addressRegistered, addressLife));

        Account result = this.service.createAccount(new Account(null, bankId, lastName, firstName,
                middleName, birthDate, passport, placeBirth,
                phone, email, addressRegistered, addressLife));

        assertEquals(new Account(id, bankId, lastName, firstName,
                middleName, birthDate, passport, placeBirth,
                phone, email, addressRegistered, addressLife), result);
        verify(this.accountRepository).save(new Account(null, bankId, lastName, firstName,
                middleName, birthDate, passport, placeBirth,
                phone, email, addressRegistered, addressLife));
        verifyNoMoreInteractions(this.accountRepository);
    }

    @Test
    void findAccountByIdThenReturnNotEmptyOptional() {
        Account account = new Account(id, bankId, lastName, firstName,
                middleName, birthDate, passport, placeBirth,
                phone, email, addressRegistered, addressLife);
        doReturn(Optional.of(account)).when(this.accountRepository)
                .findById(id);

        Optional<Account> result = this.service.findAccountById(id);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(account, result.orElseThrow());
        verify(this.accountRepository).findById(id);
        verifyNoMoreInteractions(this.accountRepository);
    }

    @Test
    void findAccountByIdThenReturnEmptyOptional() {
        Account account = new Account(id, bankId, lastName, firstName,
                middleName, birthDate, passport, placeBirth,
                phone, email, addressRegistered, addressLife);
        Optional<Account> result = this.service.findAccountById(id);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(this.accountRepository).findById(id);
        verifyNoMoreInteractions(this.accountRepository);
    }

    @Test
    void findAccountByAccountParamThenReturnIterableAccountNotEmpty() {
        Iterable<Account> accounts = LongStream.range(1L, 5L)
                .mapToObj(i -> new Account(i, bankId + i, lastName + i, firstName + i,
                        middleName + i, birthDate, passport, placeBirth,
                        phone + i, email + i, addressRegistered, addressLife))
                .toList();
        FindAccountParam findAccountParam = new FindAccountParam(lastName + 1L, firstName + 2L,
                middleName + 3L, phone + 4l, email + 5L);
        doReturn(accounts).when(this.accountRepository)
                .findAccountByLastNameOrFirstNameOrMiddleNameOrPhoneOrEmail(
                        lastName + 1L, firstName + 2L,
                        middleName + 3L, phone + 4l, email + 5L);

        Iterable<Account> result = this.service.findAccountByAccountParam(findAccountParam);

        assertEquals(accounts, result);
        verify(this.accountRepository)
                .findAccountByLastNameOrFirstNameOrMiddleNameOrPhoneOrEmail(
                        lastName + 1L, firstName + 2L,
                        middleName + 3L, phone + 4l, email + 5L);
        verifyNoMoreInteractions(this.accountRepository);
    }

    @Test
    void findAccountByAccountParamThenReturnIterableAccountEmpty() {
        FindAccountParam findAccountParam = new FindAccountParam();

        Iterable<Account> result = this.service.findAccountByAccountParam(findAccountParam);

        assertEquals(List.of(), result);
        verify(this.accountRepository)
                .findAccountByLastNameOrFirstNameOrMiddleNameOrPhoneOrEmail(null, null,
                        null, null, null);
        verifyNoMoreInteractions(this.accountRepository);
    }
}