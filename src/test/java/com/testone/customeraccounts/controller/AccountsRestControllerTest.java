package com.testone.customeraccounts.controller;

import com.testone.customeraccounts.controller.payload.NewAccountPayload;
import com.testone.customeraccounts.entity.Account;
import com.testone.customeraccounts.entity.Platform;
import com.testone.customeraccounts.service.AccountService;
import com.testone.customeraccounts.service.PlatformService;
import com.testone.customeraccounts.service.mapper.AccountMapper;
import com.testone.customeraccounts.validator.PayloadValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountsRestControllerTest {
    @Mock
    AccountService accountService;
    PlatformService platformService = mock(PlatformService.class);
    @Spy
    PayloadValidator payloadValidator = new PayloadValidator(platformService);
    @InjectMocks()
    AccountsRestController controller;

    @Test
    void beanIsNotNull() {
        assertNotNull(controller);
    }

    @Test
    void findAccountByAccountParamThenReturnIterableAccount() {
        Map<String, String> findParam = Map.of(
                "lastName", "Petrov",
                "firstName", "Ivan");
        Account account1 = Account.of()
                .id(1L)
                .lastName("Petrov")
                .firstName("Petr")
                .build();
        Account account2 = Account.of()
                .id(2L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .build();
        doReturn(List.of(account1, account2))
                .when(this.accountService).findAccountByAccountParam(findParam);
        Iterable<Account> result = this.controller.findAccountByAccountParam(findParam);
        assertThat(List.of(account1, account2)).isEqualTo(result);
    }

    @Test
    void findAccountByAccountParamThenReturnIterableEmpty() {
        Map<String, String> findParam = Map.of(
                "lastName", "Petrov",
                "firstName", "Ivan");
        doReturn(List.of())
                .when(this.accountService).findAccountByAccountParam(findParam);
        Iterable<Account> result = this.controller.findAccountByAccountParam(findParam);
        assertThat(result).isEmpty();
    }

    @Test
    void findAccountByAccountParamThenReturnNoSuchException() {
        String messageError = "customer.errors.account.find_param";
        assertThatThrownBy(() -> this.controller.findAccountByAccountParam(Map.of()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(messageError);
    }

    @Test
    void createAccountThenRequestValidReturnNewContent() throws BindException {
        String headerValue = "mail";
        Platform platform = Platform.of()
                .platformName(headerValue)
                .firstName(true)
                .email(true)
                .build();
        NewAccountPayload payload = NewAccountPayload.of()
                .firstName("Ivan")
                .email("ivan@mail.ru")
                .build();
        Account accountNew = AccountMapper.mapToAccount(payload);
        Account account = Account.of()
                .id(1L)
                .firstName("Ivan")
                .email("ivan@mail.ru")
                .build();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("http://localhost");
        doReturn(Optional.of(platform)).when(platformService)
                .findPlatformByName(headerValue);
        doReturn(account).when(this.accountService)
                .createAccount(accountNew);
        ResponseEntity<?> result = this.controller.createAccount(headerValue, payload, uriComponentsBuilder);

        assertThat(result).isNotNull();
        assertThat(HttpStatus.CREATED).isEqualTo(result.getStatusCode());
        assertThat(URI.create("http://localhost/customer-api/accounts/1"))
                .isEqualTo(result.getHeaders().getLocation());
        assertThat(account).isEqualTo(result.getBody());

        verify(this.accountService).createAccount(accountNew);
        verifyNoMoreInteractions(this.accountService);
    }

    @Test
    void createAccount_RequestIsInvalid_ReturnBadRequest() {
        String headerValue = "mail";
        Platform platform = Platform.of()
                .platformName(headerValue)
                .firstName(true)
                .email(true)
                .build();
        NewAccountPayload payload = NewAccountPayload.of()
                .firstName("Ivan")
                .build();
        doReturn(Optional.of(platform)).when(platformService)
                .findPlatformByName(headerValue);
        String messageError = "customer.errors.account.email";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("http://localhost");
        assertThatThrownBy(() -> this.controller.createAccount(headerValue, payload, uriComponentsBuilder))
                .isInstanceOf(BindException.class)
                .hasMessageContaining(messageError);
        verifyNoInteractions(accountService);
    }
}