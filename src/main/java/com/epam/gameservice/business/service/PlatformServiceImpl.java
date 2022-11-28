package com.epam.gameservice.business.service;

import com.epam.gameservice.business.annotation.InputMethodLog;
import com.epam.gameservice.business.annotation.OutputMethodLog;
import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.business.event.events.PlatformSaveEvent;
import com.epam.gameservice.business.exception.PlatformNotFoundException;
import com.epam.gameservice.business.mapper.PlatformMapper;
import com.epam.gameservice.dao.entity.Platform;
import com.epam.gameservice.dao.repository.PlatformRepository;
import com.epam.gameservice.web.dto.platforms.PlatformDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.gameservice.business.cache.CacheName.PLATFORMS;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @OutputMethodLog
    @Cacheable(PLATFORMS)
    public List<PlatformDto> findAllPlatformDtos() {
        return platformRepository.findAllPlatformDtos();
    }

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
    @Transactional
    public void save(PlatformDtoRequest request) {
        Platform platform = PlatformMapper.INSTANCE.map(request);
        platformRepository.save(platform);
        eventPublisher.publishEvent(new PlatformSaveEvent(platform.getCode()));
    }
}