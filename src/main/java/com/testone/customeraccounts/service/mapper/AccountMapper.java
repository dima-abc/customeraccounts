package com.testone.customeraccounts.service.mapper;

import com.testone.customeraccounts.controller.payload.NewAccountPayload;
import com.testone.customeraccounts.entity.Account;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AccountMapper {
    public static Account mapToAccount(NewAccountPayload newAccountPayload) {
        if (newAccountPayload == null) {
            return null;
        }
        LocalDate birthDate = newAccountPayload.getBirthDate() != null ? mapToLocalDate(newAccountPayload.getBirthDate()) : null;
        return Account.of()
                .bankId(newAccountPayload.getBankId())
                .lastName(newAccountPayload.getLastName())
                .firstName(newAccountPayload.getFirstName())
                .middleName(newAccountPayload.getMiddleName())
                .birthDate(birthDate)
                .passport(newAccountPayload.getPassport())
                .placeBirth(newAccountPayload.getPlaceBirth())
                .phone(newAccountPayload.getPhone())
                .email(newAccountPayload.getEmail())
                .addressRegistered(newAccountPayload.getAddressRegistered())
                .addressLife(newAccountPayload.getAddressLife())
                .build();
    }

    private static LocalDate mapToLocalDate(String localDate) {
        return LocalDate.parse(localDate, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
