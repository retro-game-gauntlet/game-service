package com.epam.gameservice.service;

import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.entity.Platform;
import com.epam.gameservice.mapper.PlatformMapper;
import com.epam.gameservice.repository.GameRepository;
import com.epam.gameservice.repository.PlatformRepository;
import com.epam.gameservice.service.exception.PlatformNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlatformService {

    private final PlatformRepository platformRepository;
    private final GameRepository gameRepository;

    public PlatformDto findByCode(String code) {
        return platformRepository.findByCode(code)
                .map(this::convert)
                .orElseThrow(() -> new PlatformNotFoundException(code));
    }

    private PlatformDto convert(Platform platform) {
        long gamesCount = gameRepository.countByPlatformCode(platform.getCode());
        PlatformDto platformDto = PlatformMapper.INSTANCE.map(platform, gamesCount);
        log.debug("Platform by code: {} fond: {}", platform.getCode(), platformDto);
        return platformDto;
    }
}