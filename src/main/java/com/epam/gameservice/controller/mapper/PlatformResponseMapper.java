package com.epam.gameservice.controller.mapper;

import com.epam.gameservice.controller.dto.PlatformResponse;
import com.epam.gameservice.domain.PlatformDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlatformResponseMapper {

    PlatformResponseMapper INSTANCE = Mappers.getMapper(PlatformResponseMapper.class);

    @Mapping(source = ".", target = "data.platformDto")
    PlatformResponse map(PlatformDto platformDto);
}