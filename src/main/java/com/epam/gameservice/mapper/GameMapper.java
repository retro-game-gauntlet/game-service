package com.epam.gameservice.mapper;

import com.epam.gameservice.controller.dto.games.GameDtoRequest;
import com.epam.gameservice.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    Game map(GameDtoRequest request);
}
