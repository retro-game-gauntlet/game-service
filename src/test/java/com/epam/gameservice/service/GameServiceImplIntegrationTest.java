package com.epam.gameservice.service;

import com.epam.gameservice.domain.GameDto;
import com.epam.gameservice.repository.GameRepository;
import com.epam.gameservice.tags.Spring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static com.epam.gameservice.factories.GameDtoFactory.marioDto;
import static com.epam.gameservice.factories.GameDtoRequestFactory.marioDtoRequest;
import static com.epam.gameservice.factories.PlatformFactory.nes;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Spring
@SpringBootTest(classes = ServiceTestConfig.class)
@ExtendWith(SpringExtension.class)
class GameServiceImplIntegrationTest {

    @Autowired
    private GameService gameService;

    @MockBean
    private GameRepository gameRepository;
    @MockBean
    private PlatformService platformService;

    @Test
    void shouldReturnCachedGame() {
        when(gameRepository.findGameByName("Super Mario Bros.")).thenReturn(of(marioDto()));
        GameDto gameFromDb = gameService.findGameByName("Super Mario Bros.");

        when(gameRepository.findGameByName("Super Mario Bros.")).thenReturn(of(marioDto()));
        GameDto gameFromCache = gameService.findGameByName("Super Mario Bros.");

        assertThat(gameFromCache).isSameAs(gameFromDb);
    }

    @Test
    void shouldReturnCachedGames() {
        when(gameRepository.findGamesByPlatformCode("NES")).thenReturn(Collections.singletonList(marioDto()));
        List<GameDto> gamesFormDb = gameService.findGamesByPlatformCode("NES");

        when(gameRepository.findGamesByPlatformCode("NES")).thenReturn(Collections.singletonList(marioDto()));
        List<GameDto> gamesFormCache = gameService.findGamesByPlatformCode("NES");

        assertThat(gamesFormDb).isSameAs(gamesFormCache);
    }

    @Test
    void shouldInvalidateCacheAfterGameSave() {
        when(gameRepository.findGameByName("Super Mario Bros.")).thenReturn(of(marioDto()));
        GameDto gameFromDb = gameService.findGameByName("Super Mario Bros.");
        when(platformService.findByCode("NES")).thenReturn(nes());

        gameService.save(marioDtoRequest());

        when(gameRepository.findGameByName("Super Mario Bros.")).thenReturn(of(marioDto()));
        GameDto gameFromDb1 = gameService.findGameByName("Super Mario Bros.");

        assertThat(gameFromDb).isNotSameAs(gameFromDb1);
    }
}