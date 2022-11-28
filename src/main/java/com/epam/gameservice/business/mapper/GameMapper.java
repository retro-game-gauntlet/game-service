package com.epam.gameservice.business.mapper;

import com.epam.gameservice.dao.entity.Game;
import com.epam.gameservice.web.dto.games.GameDtoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    Game map(GameDtoRequest request);
}
