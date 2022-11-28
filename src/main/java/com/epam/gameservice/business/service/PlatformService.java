package com.epam.gameservice.business.service;

import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.web.dto.platforms.PlatformDtoRequest;

import java.util.List;

public interface PlatformService {

    List<PlatformDto> findAllPlatformDtos();

    PlatformDto findPlatformDtoByCode(String code);

    void save(PlatformDtoRequest request);
}
