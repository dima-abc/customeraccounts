package com.testone.customeraccounts.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAccountParam {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;
}
