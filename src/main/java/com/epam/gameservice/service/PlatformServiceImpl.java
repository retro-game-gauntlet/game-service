package com.epam.gameservice.service;

import com.epam.gameservice.annotation.LogReturning;
import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.entity.Platform;
import com.epam.gameservice.exception.PlatformNotFoundException;
import com.epam.gameservice.mapper.PlatformMapper;
import com.epam.gameservice.repository.GameRepository;
import com.epam.gameservice.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;
    private final GameRepository gameRepository;

    @Override
    @LogReturning
    public PlatformDto findByCode(String code) {
        return platformRepository.findByCode(code)
                .map(this::convert)
                .orElseThrow(() -> new PlatformNotFoundException(code));
    }

    private PlatformDto convert(Platform platform) {
        long gamesCount = gameRepository.countByPlatformCode(platform.getCode());
        return PlatformMapper.INSTANCE.map(platform, gamesCount);
    }
}