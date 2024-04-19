package com.testone.customeraccounts.service;

import com.testone.customeraccounts.controller.payload.NewPlatform;
import com.testone.customeraccounts.controller.payload.UpdatePlatform;
import com.testone.customeraccounts.entity.Platform;

import java.util.Optional;

public interface PlatformService {
    Platform createPlatform(NewPlatform newPlatform);

    void updatePlatform(Long id, UpdatePlatform updatePlatform);

    Optional<Platform> findPlatformById(Long id);

    void deletePlatformById(Long id);

    Optional<Platform> findPlatformByName(String platformName);

    Iterable<Platform> findAllPlatform(String platformName);
}
