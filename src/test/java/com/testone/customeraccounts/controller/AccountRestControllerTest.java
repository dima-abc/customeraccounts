package com.testone.customeraccounts.controller;

import com.testone.customeraccounts.entity.Account;
import com.testone.customeraccounts.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AccountRestControllerTest {
    @Mock
    AccountService accountService;

    @InjectMocks
    AccountRestController controller;

    @Test
    void getAccountThenReturnProduct() {
        Account account = Account.of()
                .id(1L)
                .bankId(123l)
                .firstName("Ivan")
                .build();
        doReturn(Optional.of(account)).when(this.accountService).findAccountById(1L);
        Account result = this.controller.getAccount(1L);
        assertEquals(account, result);
    }

    @Test
    void getAccountNotExistsThenThrowNoSuchElementException() {
        String messageError = "Учетная запись не найдена";
        assertThatThrownBy(() -> this.controller.getAccount(1L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(messageError);
    }

    @Test
    void findAccountThenReturnAccount() {
        Account account = Account.of()
                .id(1L)
                .bankId(123l)
                .firstName("Ivan")
                .build();
        Account result = this.controller.findAccount(account);
        assertThat(result).isEqualTo(account);
    }
}