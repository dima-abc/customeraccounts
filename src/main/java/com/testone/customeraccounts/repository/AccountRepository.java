package com.testone.customeraccounts.repository;

import com.testone.customeraccounts.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Iterable<Account> findAccountByLastNameOrFirstNameOrMiddleNameOrPhoneOrEmail(String lastName,
                                                                             String firstName,
                                                                             String middleName,
                                                                             String phone,
                                                                             String email);
}
