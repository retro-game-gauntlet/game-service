package com.epam.gameservice.service;

import com.epam.gameservice.domain.GameDto;
import com.epam.gameservice.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

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
        GameDto mario = new GameDto("Super Mario Bros.", LocalDate.of(1985, 9, 13));
        when(gameRepository.findGamesByPlatformCode("nes")).thenReturn(singletonList(mario));

        List<GameDto> games = gameService.findGamesByPlatformCode("nes");

        assertThat(games).containsExactly(mario);
    }
}