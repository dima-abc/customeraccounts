package com.testone.customeraccounts.controller.payload;

import com.testone.customeraccounts.validator.HeaderValidGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO модель для сохранения учетной записи по x-Source = mail
 * обязательные поля только: имя и email
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class NewAccountPayload {
    @NotNull(message = "Поле bankId обязательное.",
            groups = {HeaderValidGroup.Bank.class, HeaderValidGroup.Gosuslugi.class})
    private Long bankId;
    @NotNull(message = "Поле firstName обязательное.",
            groups = {HeaderValidGroup.Mail.class, HeaderValidGroup.Bank.class, HeaderValidGroup.Gosuslugi.class})
    private String firstName;
    @NotNull(message = "Поле lastName обязательное.",
            groups = {HeaderValidGroup.Bank.class, HeaderValidGroup.Gosuslugi.class})
    private String lastName;
    @NotNull(message = "Поле middleName обязательное.",
            groups = {HeaderValidGroup.Bank.class, HeaderValidGroup.Gosuslugi.class})
    private String middleName;
    @NotNull(message = "Поле birthDate обязательное и должно соответствовать формату dd-MM-yyyy",
            groups = {HeaderValidGroup.Bank.class, HeaderValidGroup.Gosuslugi.class})
    @Pattern(message = "Поле birthDate обязательное и должно соответствовать формату dd-MM-yyyy",
            regexp = "^\\d{4}-\\d{2}-\\d{2}",
            flags = Pattern.Flag.UNICODE_CASE,
            groups = {HeaderValidGroup.Bank.class, HeaderValidGroup.Gosuslugi.class})
    private String birthDate;
    @NotNull(message = "Поле passport обязательное и должно соответствовать формату XXXX XXXXXX",
            groups = {HeaderValidGroup.Bank.class, HeaderValidGroup.Gosuslugi.class})
    @Pattern(message = "Поле passport обязательное и должно соответствовать формату XXXX XXXXXX",
            regexp = "\\d{4} \\d{6}",
            flags = Pattern.Flag.UNICODE_CASE,
            groups = {HeaderValidGroup.Bank.class, HeaderValidGroup.Gosuslugi.class})
    private String passport;
    @NotNull(message = "Поле placeBirth обязательное.",
            groups = {HeaderValidGroup.Gosuslugi.class})
    private String placeBirth;
    @NotNull(message = "Поле phone обязательное и должно соответствовать формату 7XXXXXXXXXX",
            groups = {HeaderValidGroup.Mobile.class, HeaderValidGroup.Gosuslugi.class})
    @Pattern(message = "Поле phone обязательное и должно соответствовать формату 7XXXXXXXXXX",
            regexp = "^7\\d{10}",
            flags = Pattern.Flag.UNICODE_CASE,
            groups = {HeaderValidGroup.Mobile.class, HeaderValidGroup.Gosuslugi.class})
    private String phone;
    @NotNull(message = "Поле email обязательное.",
            groups = {HeaderValidGroup.Mail.class})
    private String email;
    @NotNull(message = "Поле addressRegistered обязательное.",
            groups = {HeaderValidGroup.Gosuslugi.class})
    private String addressRegistered;
    private String addressLife;
}
