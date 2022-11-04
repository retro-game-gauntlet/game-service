package com.epam.gameservice.service;

import com.epam.gameservice.controller.dto.platforms.PlatformDtoRequest;
import com.epam.gameservice.domain.PlatformDto;

public interface PlatformService {

    PlatformDto findByCode(String code);

    void save(PlatformDtoRequest request);
}
