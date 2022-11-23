package com.epam.gameservice.service;

import com.epam.gameservice.annotation.InputMethodLog;
import com.epam.gameservice.annotation.OutputMethodLog;
import com.epam.gameservice.controller.dto.platforms.PlatformDtoRequest;
import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.entity.Platform;
import com.epam.gameservice.exception.PlatformNotFoundException;
import com.epam.gameservice.mapper.PlatformMapper;
import com.epam.gameservice.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.epam.gameservice.cache.CacheName.PLATFORMS;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;

    @Override
    @InputMethodLog
    @OutputMethodLog
    @Cacheable(PLATFORMS)
    public PlatformDto findPlatformDtoByCode(String code) {
        return platformRepository.findPlatformDtoByCode(code)
                .orElseThrow(() -> new PlatformNotFoundException(code));
    }

    @Override
    @InputMethodLog
    @OutputMethodLog
    @Cacheable(PLATFORMS)
    public Platform findByCode(String code) {
        return platformRepository.findByCode(code)
                .orElseThrow(() -> new PlatformNotFoundException(code));
    }

    @Override
    @InputMethodLog
    @Transactional
    public void save(PlatformDtoRequest request) {
        Platform platform = PlatformMapper.INSTANCE.map(request);
        platformRepository.save(platform);
    }
}