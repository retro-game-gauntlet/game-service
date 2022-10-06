package com.epam.gameservice.controller;

import com.epam.gameservice.controller.dto.PlatformResponse;
import com.epam.gameservice.controller.mapper.PlatformResponseMapper;
import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService platformService;

    @GetMapping("/{code}")
    public ResponseEntity<PlatformResponse> getByPlatform(@PathVariable String code) {
        PlatformDto platformDto = platformService.findByCode(code);
        PlatformResponse response = PlatformResponseMapper.INSTANCE.map(platformDto);
        return ResponseEntity.ok(response);
    }
}