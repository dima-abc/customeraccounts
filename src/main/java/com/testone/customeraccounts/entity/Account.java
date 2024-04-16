package com.testone.customeraccounts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "of")
@Entity
@Table(schema = "account_service", name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bank_id")
    private Long bankId;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "passport", unique = true)
    private String passport;
    @Column(name = "place_birth")
    private String placeBirth;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address_registered")
    private String addressRegistered;
    @Column(name = "address_life")
    private String addressLife;
}
