package com.testone.customeraccounts.validator;

import com.testone.customeraccounts.controller.payload.NewAccountPayload;
import com.testone.customeraccounts.entity.Platform;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

class AccountValidatorTest {
    @Test
    public void test_validPayloadWithAllRequiredFields() {
        Platform platform = new Platform();
        platform.setBankId(true);
        platform.setFirstName(true);
        platform.setLastName(true);
        AccountValidator validator = new AccountValidator(platform);
        NewAccountPayload payload = new NewAccountPayload();
        payload.setFirstName("Ivan");
        payload.setLastName("Ivanov");
        Errors errors = new BeanPropertyBindingResult(payload, "payload");
        validator.validate(payload, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void test_validPayloadWithOnlyOptionalFields() {
        Platform platform = new Platform();
        platform.setBankId(false);
        platform.setFirstName(false);
        platform.setLastName(false);
        platform.setMiddleName(false);
        platform.setBirthDate(false);
        platform.setPassport(false);
        platform.setPlaceBirth(false);
        platform.setPhone(false);
        platform.setEmail(false);
        platform.setAddressRegistered(false);
        platform.setAddressLife(false);
        AccountValidator validator = new AccountValidator(platform);
        NewAccountPayload payload = new NewAccountPayload();
        payload.setBankId(null);
        payload.setFirstName(null);
        payload.setLastName(null);
        payload.setMiddleName(null);
        payload.setBirthDate(null);
        payload.setPassport(null);
        payload.setPlaceBirth(null);
        payload.setPhone(null);
        payload.setEmail(null);
        payload.setAddressRegistered(null);
        payload.setAddressLife(null);
        Errors errors = new BeanPropertyBindingResult(payload, "payload");
        validator.validate(payload, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void test_validPayloadWithMissingOptionalFields() {
        Platform platform = new Platform();
        platform.setBankId(true);
        platform.setFirstName(true);
        AccountValidator validator = new AccountValidator(platform);
        NewAccountPayload payload = new NewAccountPayload();
        payload.setBankId(1234567890L);
        payload.setFirstName("Misha");
        Errors errors = new BeanPropertyBindingResult(payload, "payload");
        validator.validate(payload, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void test_validPayloadWithEmptyOptionalFields() {
        Platform platform = new Platform();
        platform.setBankId(true);
        platform.setFirstName(true);
        platform.setLastName(true);
        platform.setMiddleName(true);
        platform.setBirthDate(true);
        platform.setPassport(true);
        platform.setPlaceBirth(true);
        platform.setPhone(true);
        platform.setEmail(true);
        platform.setAddressRegistered(true);
        platform.setAddressLife(true);
        AccountValidator validator = new AccountValidator(platform);
        NewAccountPayload payload = new NewAccountPayload();
        payload.setBankId(1234567890L);
        payload.setFirstName("Misha");
        payload.setLastName("Mishin");
        payload.setMiddleName("Tolik");
        payload.setBirthDate("2022-01-01");
        payload.setPassport("1234 567890");
        payload.setPlaceBirth("Moscow");
        payload.setPhone("7123456789");
        payload.setEmail("mail@mail.ru");
        payload.setAddressRegistered("address");
        payload.setAddressLife("");
        Errors errors = new BeanPropertyBindingResult(payload, "payload");
        validator.validate(payload, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void test_validPayloadWithWhitespaceOptionalFields() {
        Platform platform = new Platform();
        platform.setBankId(true);
        platform.setFirstName(true);
        platform.setLastName(true);
        platform.setMiddleName(true);
        platform.setBirthDate(true);
        platform.setPassport(true);
        platform.setPlaceBirth(true);
        platform.setPhone(true);
        platform.setEmail(true);
        platform.setAddressRegistered(true);
        platform.setAddressLife(true);
        AccountValidator validator = new AccountValidator(platform);
        NewAccountPayload payload = new NewAccountPayload();
        payload.setBankId(1234567890L);
        payload.setFirstName("Misha");
        payload.setLastName("Mishin");
        payload.setMiddleName("Tolik");
        payload.setBirthDate("2022-01-01");
        payload.setPassport("1234 567890");
        payload.setPlaceBirth("Moscow");
        payload.setPhone("7123456789");
        payload.setEmail("mail@mail.ru");
        payload.setAddressRegistered("address");
        payload.setAddressLife("   ");
        Errors errors = new BeanPropertyBindingResult(payload, "payload");
        validator.validate(payload, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void test_missingRequiredFields() {
        Platform platform = new Platform();
        platform.setBankId(true);
        platform.setFirstName(true);
        platform.setLastName(true);
        platform.setMiddleName(true);
        platform.setBirthDate(true);
        platform.setPassport(true);
        platform.setPlaceBirth(true);
        platform.setPhone(true);
        platform.setEmail(true);
        platform.setAddressRegistered(true);
        platform.setAddressLife(true);
        AccountValidator validator = new AccountValidator(platform);
        NewAccountPayload payload = new NewAccountPayload();
        Errors errors = new BeanPropertyBindingResult(payload, "payload");
        validator.validate(payload, errors);
        assertTrue(errors.hasErrors());
        assertEquals(11, errors.getErrorCount());
    }

    @Test
    public void test_missingSomeRequiredFields() {
        Platform platform = new Platform();
        platform.setBankId(true);
        platform.setFirstName(true);
        platform.setLastName(true);
        platform.setMiddleName(true);
        platform.setBirthDate(true);
        platform.setPassport(true);
        platform.setPlaceBirth(true);
        platform.setPhone(true);
        platform.setEmail(true);
        platform.setAddressRegistered(true);
        platform.setAddressLife(true);
        AccountValidator validator = new AccountValidator(platform);
        NewAccountPayload payload = new NewAccountPayload();
        payload.setBankId(1234567890L);
        payload.setFirstName("Misha");
        payload.setLastName("Mishin");
        payload.setMiddleName(null);
        payload.setBirthDate("2022-01-01");
        payload.setPassport("1234 567890");
        payload.setPlaceBirth("Moscow");
        payload.setPhone("7123456789");
        payload.setEmail("mail@mail.ru");
        payload.setAddressRegistered(null);
        payload.setAddressLife("address");
        Errors errors = new BeanPropertyBindingResult(payload, "payload");
        validator.validate(payload, errors);
        assertTrue(errors.hasErrors());
        assertEquals(3, errors.getErrorCount());
    }
}