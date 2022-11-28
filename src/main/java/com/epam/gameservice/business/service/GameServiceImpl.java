package com.epam.gameservice.business.service;

import com.epam.gameservice.business.annotation.InputMethodLog;
import com.epam.gameservice.business.annotation.OutputMethodLog;
import com.epam.gameservice.business.domain.GameDto;
import com.epam.gameservice.business.event.events.GameSaveEvent;
import com.epam.gameservice.business.exception.GameNotFoundException;
import com.epam.gameservice.business.mapper.GameMapper;
import com.epam.gameservice.dao.entity.Game;
import com.epam.gameservice.dao.entity.Platform;
import com.epam.gameservice.dao.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.gameservice.business.cache.CacheName.GAMES;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlatformService platformService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @InputMethodLog
    @OutputMethodLog
    @Cacheable(GAMES)
    public List<GameDto> findGamesByPlatformCode(String platformCode) {
        return gameRepository.findGamesByPlatformCode(platformCode);
    }

    @Override
    @InputMethodLog
    @OutputMethodLog
    @Cacheable(GAMES)
    public GameDto findGameByName(String name) {
        return gameRepository.findGameByName(name)
                .orElseThrow(() -> new GameNotFoundException(name));
    }

    @Override
    @InputMethodLog
    @Transactional
    public void save(GameDto gameDto) {
        Platform platform = platformService.findByCode(gameDto.platformCode());
        Game game = GameMapper.INSTANCE.map(gameDto);
        platform.addGame(game);
        eventPublisher.publishEvent(new GameSaveEvent(gameDto.name()));
    }
}
