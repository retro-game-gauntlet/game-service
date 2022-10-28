package com.epam.gameservice.service;

import com.epam.gameservice.domain.GameDto;
import com.epam.gameservice.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.epam.gameservice.factories.GameDtoFactory.marioDto;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    void shouldFindGamesByPlatformCode() {
        when(gameRepository.findGamesByPlatformCode("nes")).thenReturn(singletonList(marioDto()));

        List<GameDto> games = gameService.findGamesByPlatformCode("nes");

        assertThat(games).containsExactly(marioDto());
    }
}