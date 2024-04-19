package com.testone.customeraccounts.service;

import com.testone.customeraccounts.controller.payload.NewPlatform;
import com.testone.customeraccounts.controller.payload.UpdatePlatform;
import com.testone.customeraccounts.entity.Platform;
import com.testone.customeraccounts.repository.PlatformRepository;
import com.testone.customeraccounts.service.mapper.PlatformMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImplPlatformService implements PlatformService {

    private final PlatformRepository repository;

    @Transactional
    @Override
    public Platform createPlatform(NewPlatform newPlatform) {
        Platform platform = PlatformMapper.mapToPlatform(newPlatform);
        return this.repository.save(platform);
    }

    @Transactional
    @Override
    public void updatePlatform(Long id, UpdatePlatform updatePlatform) {
        this.repository.findById(id)
                .ifPresentOrElse(platform -> {
                    platform.setPlatformName(updatePlatform.getPlatformName());
                    platform.setBankId(updatePlatform.isBankId());
                    platform.setLastName(updatePlatform.isLastName());
                    platform.setFirstName(updatePlatform.isFirstName());
                    platform.setMiddleName(updatePlatform.isMiddleName());
                    platform.setBirthDate(updatePlatform.isBirthDate());
                    platform.setPassport(updatePlatform.isPassport());
                    platform.setPlaceBirth(updatePlatform.isPlaceBirth());
                    platform.setPhone(updatePlatform.isPhone());
                    platform.setEmail(updatePlatform.isEmail());
                    platform.setAddressRegistered(updatePlatform.isAddressRegistered());
                    platform.setAddressLife(updatePlatform.isAddressLife());
                }, () -> {
                    throw new NoSuchElementException("${customer.errors.platform.not_found}");
                });
    }

    @Override
    public Optional<Platform> findPlatformById(Long id) {
        return this.repository.findById(id);
    }

    @Transactional
    @Override
    public void deletePlatformById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<Platform> findPlatformByName(String platformName) {
        return this.repository.findPlatformByPlatformNameIgnoreCase(platformName);
    }

    @Override
    public Iterable<Platform> findAllPlatform(String platformName) {
        if (platformName != null && !platformName.isBlank()) {
            return repository.findPlatformByPlatformNameLikeIgnoreCase(platformName);
        }
        return this.repository.findAll();
    }
}
