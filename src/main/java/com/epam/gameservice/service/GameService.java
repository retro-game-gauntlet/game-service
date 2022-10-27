package com.epam.gameservice.service;

import com.epam.gameservice.domain.GameDto;

import java.util.List;

public interface GameService {

    List<GameDto> findGamesByPlatformCode(String platformCode);
}
