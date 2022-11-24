package com.epam.gameservice.business.mapper;

import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.dao.entity.Platform;
import com.epam.gameservice.web.dto.platforms.PlatformDtoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlatformMapper {

    PlatformMapper INSTANCE = Mappers.getMapper(PlatformMapper.class);

    PlatformDto map(Platform platform, long gamesCount);

    Platform map(PlatformDtoRequest request);
}