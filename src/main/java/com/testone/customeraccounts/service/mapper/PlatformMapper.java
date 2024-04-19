package com.testone.customeraccounts.service.mapper;

import com.testone.customeraccounts.controller.payload.NewPlatform;
import com.testone.customeraccounts.entity.Platform;

public class PlatformMapper {

    public static Platform mapToPlatform(NewPlatform newPlatform) {
        if (newPlatform == null) {
            return null;
        }
        return Platform.of()
                .platformName(newPlatform.getPlatformName())
                .bankId(newPlatform.isBankId())
                .lastName(newPlatform.isLastName())
                .firstName(newPlatform.isFirstName())
                .middleName(newPlatform.isMiddleName())
                .birthDate(newPlatform.isBirthDate())
                .passport(newPlatform.isPassport())
                .placeBirth(newPlatform.isPlaceBirth())
                .phone(newPlatform.isPhone())
                .email(newPlatform.isEmail())
                .addressRegistered(newPlatform.isAddressRegistered())
                .addressLife(newPlatform.isAddressLife())
                .build();
    }
}
