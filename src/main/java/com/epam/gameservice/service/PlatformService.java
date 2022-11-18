package com.epam.gameservice.service;

import com.epam.gameservice.controller.dto.platforms.PlatformDtoRequest;
import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.entity.Platform;

public interface PlatformService {

    PlatformDto findPlatformDtoByCode(String code);

    Platform findByCode(String code);

    void save(PlatformDtoRequest request);
}
