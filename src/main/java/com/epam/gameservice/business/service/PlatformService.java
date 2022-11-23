package com.epam.gameservice.business.service;

import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.dao.entity.Platform;
import com.epam.gameservice.web.dto.platforms.PlatformDtoRequest;

public interface PlatformService {

    PlatformDto findPlatformDtoByCode(String code);

    Platform findByCode(String code);

    void save(PlatformDtoRequest request);
}
