package com.epam.gameservice.service;

import com.epam.gameservice.annotation.LogReturning;
import com.epam.gameservice.controller.dto.games.GameDtoRequest;
import com.epam.gameservice.domain.GameDto;
import com.epam.gameservice.entity.Game;
import com.epam.gameservice.entity.Platform;
import com.epam.gameservice.exception.GameNotFoundException;
import com.epam.gameservice.mapper.GameMapper;
import com.epam.gameservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlatformService platformService;

    @Override
    @LogReturning
    public List<GameDto> findGamesByPlatformCode(String platformCode) {
        return gameRepository.findGamesByPlatformCode(platformCode);
    }

    @Override
    @LogReturning
    public GameDto findGameByName(String name) {
        return gameRepository.findGameByName(name)
                .orElseThrow(() -> new GameNotFoundException(name));
    }

    @Override
    @Transactional
    public void save(GameDtoRequest request) {
        Platform platform = platformService.findByCode(request.platformCode());
        Game game = GameMapper.INSTANCE.map(request);
        platform.addGame(game);
    }
}
