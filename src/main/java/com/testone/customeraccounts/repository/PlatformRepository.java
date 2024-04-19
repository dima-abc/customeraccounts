package com.testone.customeraccounts.repository;

import com.testone.customeraccounts.entity.Platform;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlatformRepository extends CrudRepository<Platform, Long> {
    Optional<Platform> findPlatformByPlatformNameIgnoreCase(String platformName);
    Iterable<Platform> findPlatformByPlatformNameLikeIgnoreCase(String platformName);
}
