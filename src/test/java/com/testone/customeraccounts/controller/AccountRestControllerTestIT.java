package com.testone.customeraccounts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class AccountRestControllerTestIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("/sql/accounts_insert.sql")
    void findAccountThenReturnAccount() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/customer-api/accounts/1");
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {"id":1,
                        "bankId":111111,
                        "lastName":"Петров",
                        "firstName":"Петр",
                        "middleName":"Петрович",
                        "birthDate":"1975-01-01",
                        "passport":"1111 111111",
                        "placeBirth":"г.Калуга",
                        "phone":"71001001900",
                        "email":"petrov@mail.ru",
                        "addressRegistered":"г.Калуга",
                        "addressLife":"г.Калуга"}"""
                ));
    }

    @Test
    void findAccountThenReturnErrorMessage() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/customer-api/accounts/1");
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(content().json("""
                        {"type":"about:blank","title":"Not Found",
                        "status":404,
                        "detail":"Учетная запись не найдена",
                        "instance":"/customer-api/accounts/1"}
                        """));
    }
}
