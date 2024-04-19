package com.testone.customeraccounts.controller;


import com.testone.customeraccounts.controller.payload.NewPlatform;
import com.testone.customeraccounts.entity.Platform;
import com.testone.customeraccounts.service.PlatformService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("customer-api/platforms")
@RequiredArgsConstructor
public class PlatformsRestController {
    private final PlatformService service;

    @GetMapping
    public Iterable<Platform> findByName(@RequestParam(required = false, name = "p-name") String platformName) {
        return service.findAllPlatform(platformName);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPlatform(@Valid @RequestBody NewPlatform newPlatform,
                                            BindingResult bindingResult,
                                            UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Platform platform = this.service.createPlatform(newPlatform);
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/customer-api/platforms/{platformId}")
                            .build(Map.of("platformId", platform.getId())))
                    .body(platform);
        }
    }
}
