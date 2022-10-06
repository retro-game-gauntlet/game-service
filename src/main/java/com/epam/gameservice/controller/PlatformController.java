package com.epam.gameservice.controller;

import com.epam.gameservice.entity.Platform;
import com.epam.gameservice.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformRepository platformRepository;

    @GetMapping("/{code}")
    public Platform getByPlatform(@PathVariable String code) {
        return platformRepository.findByCode(code);
    }
}