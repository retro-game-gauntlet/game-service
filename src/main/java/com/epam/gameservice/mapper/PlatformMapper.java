package com.epam.gameservice.mapper;

import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.entity.Platform;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlatformMapper {

    PlatformMapper INSTANCE = Mappers.getMapper(PlatformMapper.class);

    PlatformDto map(Platform platform, long gamesCount);
}