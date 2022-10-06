package com.epam.gameservice.service;

import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.entity.Platform;
import com.epam.gameservice.mapper.PlatformMapper;
import com.epam.gameservice.repository.GameRepository;
import com.epam.gameservice.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlatformService {

    private final PlatformRepository platformRepository;
    private final GameRepository gameRepository;

    public PlatformDto findByCode(String code) {
        Platform platform = platformRepository.findByCode(code);
        long gamesCount = gameRepository.countByPlatformCode(code);

        return PlatformMapper.INSTANCE.map(platform, gamesCount);
    }
}