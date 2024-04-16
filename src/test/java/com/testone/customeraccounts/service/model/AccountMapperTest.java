package com.testone.customeraccounts.service.model;

import com.testone.customeraccounts.controller.payload.NewAccountPayload;
import com.testone.customeraccounts.entity.Account;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {
    @Test
    void mapToAccountThenReturnAccount() {
        NewAccountPayload accountPayload = NewAccountPayload.of()
                .bankId(123L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Pertrovich")
                .birthDate("1950-10-22")
                .passport("2222 333333")
                .placeBirth("c.Anapa")
                .phone("71231234567")
                .email("ivan@mail.ru")
                .addressRegistered("c.Moscow")
                .addressLife("c.Omsk")
                .build();
        Account expect = Account.of()
                .bankId(123L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Pertrovich")
                .birthDate(LocalDate.of(1950, 10, 22))
                .passport("2222 333333")
                .placeBirth("c.Anapa")
                .phone("71231234567")
                .email("ivan@mail.ru")
                .addressRegistered("c.Moscow")
                .addressLife("c.Omsk")
                .build();

        Account result = AccountMapper.mapToAccount(accountPayload);
        assertEquals(expect, result);
    }
}