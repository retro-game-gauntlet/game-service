package com.epam.gameservice.service;

import com.epam.gameservice.domain.GameDto;
import com.epam.gameservice.entity.Platform;
import com.epam.gameservice.exception.GameNotFoundException;
import com.epam.gameservice.repository.GameRepository;
import com.epam.gameservice.tags.Junit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.epam.gameservice.factories.GameDtoFactory.marioDto;
import static com.epam.gameservice.factories.GameDtoRequestFactory.marioDtoRequest;
import static com.epam.gameservice.factories.PlatformFactory.nes;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@Junit
@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GameRepository gameRepository;
    @Mock
    private PlatformService platformService;

    @Test
    void shouldFindGamesByPlatformCode() {
        when(gameRepository.findGamesByPlatformCode("nes")).thenReturn(singletonList(marioDto()));

        List<GameDto> games = gameService.findGamesByPlatformCode("nes");

        assertThat(games).containsExactly(marioDto());
    }

    @Test
    void shouldFindGameByName() {
        when(gameRepository.findGameByName("Super Mario Bros.")).thenReturn(of(marioDto()));

        GameDto game = gameService.findGameByName("Super Mario Bros.");

        assertThat(game).isEqualTo(marioDto());
    }

    @Test
    void shouldThrowExceptionWhenGameNotFound() {
        when(gameRepository.findGameByName("qwe")).thenReturn(empty());

        assertThatThrownBy(() -> gameService.findGameByName("qwe"))
                .isInstanceOf(GameNotFoundException.class)
                .hasMessageContaining("qwe");
    }

    @Test
    void shouldSaveGame() {
        Platform nes = nes();
        when(platformService.findByCode("NES")).thenReturn(nes);

        gameService.save(marioDtoRequest());

        assertThat(nes.getGames())
                .allMatch(game ->
                        game.getName().equals("Super Mario Bros.") && game.getPlatform().getCode().equals("NES")
                );
    }
}