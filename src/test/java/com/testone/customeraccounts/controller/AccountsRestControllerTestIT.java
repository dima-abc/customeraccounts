package com.testone.customeraccounts.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class AccountsRestControllerTestIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("/sql/accounts_insert.sql")
    void findAccountByAccountParamReturnIterableAccount() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/customer-api/accounts")
                .param("lastName", "Петров")
                .param("firstName", "Карп");
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        [{"bankId":111111,"lastName":"Петров",
                        "firstName":"Петр","middleName":"Петрович","birthDate":"1975-01-01",
                        "passport":"1111 111111","placeBirth":"г.Калуга","phone":"71001001900",
                        "email":"petrov@mail.ru","addressRegistered":"г.Калуга","addressLife":"г.Калуга"},
                        {"bankId":444444,"lastName":"Карпун",
                        "firstName":"Карп","middleName":"Поликарпович","birthDate":"2004-04-21",
                        "passport":"4444 444444","placeBirth":"г.Майкоп","phone":"74004004400",
                        "email":"karp@mail.ru","addressRegistered":"г.Майкоп","addressLife":"г.Майкоп"}]
                        """, false));
    }

    @Test
    @Sql("/sql/accounts_insert.sql")
    void findAccountByAccountParamReturnIterableAccountEmpty() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/customer-api/accounts")
                .param("lastName", "XXX")
                .param("firstName", "XXX");
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        []
                        """));
    }

    @Test
    void findAccountByAccountParamReturnBadRequest() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/customer-api/accounts");
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createAccountRequestValidReturnNewAccount() throws Exception {
        String headerName = "x-Source";
        var requestBuilder = MockMvcRequestBuilders.post("/customer-api/accounts")
                .header(headerName, "mail")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"firstName": "Ivan","email": "ivan@email"}
                        """);
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/customer-api/accounts/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {"id":1,"bankId":null,"lastName":null,
                        "firstName":"Ivan","middleName":null,"birthDate":null,
                        "passport":null,"placeBirth":null,"phone":null,
                        "email":"ivan@email","addressRegistered":null,"addressLife":null}
                        """));
    }

    @Test
    void createAccountRequestValidReturnBadRequest() throws Exception {
        String headerName = "x-Source";
        var requestBuilder = MockMvcRequestBuilders.post("/customer-api/accounts")
                .header(headerName, "mail")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"firstName": "Ivan"}
                        """);
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(content().json("""
                        {"type":"about:blank","title":"Bad Request",
                        "status":400,
                        "detail":"email: Поле email обязательное.",
                        "instance":"/customer-api/accounts"}
                        """));
    }
}
