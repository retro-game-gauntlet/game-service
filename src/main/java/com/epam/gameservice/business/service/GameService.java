package com.epam.gameservice.business.service;

import com.epam.gameservice.business.domain.GameDto;

import java.util.List;

public interface GameService {

    List<GameDto> findGamesByPlatformCode(String platformCode);

    GameDto findGameByName(String name);

    void save(GameDto gameDto);
}
