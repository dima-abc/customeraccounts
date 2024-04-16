package com.testone.customeraccounts.validator;

import com.testone.customeraccounts.controller.payload.NewAccountPayload;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PayloadValidatorTest {
    private PayloadValidator payloadValidator = new PayloadValidator();
    private static final String HEADER_MAIL = "mail";
    private static final String HEADER_MOBILE = "mobile";
    private static final String HEADER_BANK = "bank";
    private static final String HEADER_GOSUSLUGI = "gosuslugi";

    @Test
    void isValidThenReturnNoSuchElementException() {
        String badHeader = "badHeader";
        String message = "Заголовок %s не найден".formatted(badHeader);

        assertThatThrownBy(() -> payloadValidator.isValid(badHeader, new NewAccountPayload()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(message);
    }

    @Test
    void isValidHeaderMailThenReturnExceptionNotNullFirstNameAndMail() {
        NewAccountPayload newAccountPayload = new NewAccountPayload();
        String messageFirsName = "Поле firstName обязательное.";
        String messageEmail = "Поле email обязательное.";
        assertThatThrownBy(() -> payloadValidator.isValid(HEADER_MAIL, newAccountPayload))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(messageFirsName)
                .hasMessageContaining(messageEmail);
    }

    @Test
    void isValidHeaderMailThenReturnExceptionNotNullFirstName() {
        NewAccountPayload newAccountPayload = NewAccountPayload.of()
                .phone("79002003040")
                .build();
        String messageFirsName = "Поле firstName обязательное.";
        assertThatThrownBy(() -> payloadValidator.isValid(HEADER_MAIL, newAccountPayload))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(messageFirsName);
    }

    @Test
    void isValidHeaderMailThenReturnExceptionNotNullMail() {
        NewAccountPayload newAccountPayload = new NewAccountPayload();
        String messageEmail = "Поле email обязательное.";
        assertThatThrownBy(() -> payloadValidator.isValid(HEADER_MAIL, newAccountPayload))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(messageEmail);
    }

    @Test
    void isValidHeaderMobileThenReturnExceptionNotNullPhone() {
        NewAccountPayload newAccountPayload = new NewAccountPayload();
        String messagePhone = "Поле phone обязательное и должно соответствовать формату 7XXXXXXXXXX";
        assertThatThrownBy(() -> payloadValidator.isValid(HEADER_MOBILE, newAccountPayload))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(messagePhone);
    }

    @Test
    void isValidHeaderMobileThenReturnExceptionNotNullPhoneIsNoFormat() {
        NewAccountPayload newAccountPayload = NewAccountPayload.of()
                .phone("12341234567")
                .build();
        String messagePhone = "Поле phone обязательное и должно соответствовать формату 7XXXXXXXXXX";
        assertThatThrownBy(() -> payloadValidator.isValid(HEADER_MOBILE, newAccountPayload))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(messagePhone);
    }

    @Test
    void isValidHeaderBankThenReturnExceptionNotNullValidFiledBank() {
        NewAccountPayload newAccountPayload = new NewAccountPayload();
        String messageBankId = "Поле bankId обязательное.";
        String messageFirstName = "Поле firstName обязательное.";
        String messageLastName = "Поле lastName обязательное.";
        String messageMiddleName = "Поле middleName обязательное.";
        String messageBirthDate = "Поле birthDate обязательное и должно соответствовать формату dd-MM-yyyy";
        String messagePassport = "Поле passport обязательное и должно соответствовать формату XXXX XXXXXX";
        assertThatThrownBy(() -> payloadValidator.isValid(HEADER_BANK, newAccountPayload))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(messageBankId)
                .hasMessageContaining(messageFirstName)
                .hasMessageContaining(messageLastName)
                .hasMessageContaining(messageMiddleName)
                .hasMessageContaining(messageBirthDate)
                .hasMessageContaining(messagePassport);
    }

    @Test
    void isValidHeaderGosuslugiThenReturnExceptionNotNullValidFiledGosuslugi() {
        NewAccountPayload newAccountPayload = new NewAccountPayload();
        String messageBankId = "Поле bankId обязательное.";
        String messageFirstName = "Поле firstName обязательное.";
        String messageLastName = "Поле lastName обязательное.";
        String messageMiddleName = "Поле middleName обязательное.";
        String messageBirthDate = "Поле birthDate обязательное и должно соответствовать формату dd-MM-yyyy";
        String messagePassport = "Поле passport обязательное и должно соответствовать формату XXXX XXXXXX";
        String messagePlaceBirth = "Поле placeBirth обязательное.";
        String messagePhone = "Поле phone обязательное и должно соответствовать формату 7XXXXXXXXXX";
        String messageAddressRegistered = "Поле addressRegistered обязательное.";
        assertThatThrownBy(() -> payloadValidator.isValid(HEADER_GOSUSLUGI, newAccountPayload))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(messageBankId)
                .hasMessageContaining(messageFirstName)
                .hasMessageContaining(messageLastName)
                .hasMessageContaining(messageMiddleName)
                .hasMessageContaining(messageBirthDate)
                .hasMessageContaining(messagePassport)
                .hasMessageContaining(messagePlaceBirth)
                .hasMessageContaining(messagePhone)
                .hasMessageContaining(messageAddressRegistered);
    }


}