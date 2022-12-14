package com.epam.gameservice.business.service;

import com.epam.gameservice.business.domain.GameDto;
import com.epam.gameservice.business.event.events.GameSaveEvent;
import com.epam.gameservice.business.exception.GameNotFoundException;
import com.epam.gameservice.business.exception.PlatformNotFoundException;
import com.epam.gameservice.dao.entity.Platform;
import com.epam.gameservice.dao.repository.GameRepository;
import com.epam.gameservice.dao.repository.PlatformRepository;
import com.epam.gameservice.tags.Junit;
import com.epam.gameservice.web.dto.games.GameDtoRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;

import static com.epam.gameservice.factories.GameDtoFactory.marioDto;
import static com.epam.gameservice.factories.GameDtoRequestFactory.marioDtoRequest;
import static com.epam.gameservice.factories.PlatformFactory.nes;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Junit
@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GameRepository gameRepository;
    @Mock
    private PlatformRepository platformRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Test
    void shouldFindGamesByPlatformCode() {
        when(gameRepository.findGamesByPlatformCode("nes")).thenReturn(singletonList(marioDto()));
        when(platformRepository.existsByCodeIgnoreCase("nes")).thenReturn(true);

        List<GameDto> games = gameService.findGamesByPlatformCode("nes");

        assertThat(games).containsExactly(marioDto());
    }

    @Test
    void shouldThrowExceptionWhenGamesRequestedForIncorrectPlatform() {
        when(platformRepository.existsByCodeIgnoreCase("qwe")).thenReturn(false);

        assertThatThrownBy(() -> gameService.findGamesByPlatformCode("qwe"))
                .isInstanceOf(PlatformNotFoundException.class)
                .hasMessageContaining("qwe");
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
        when(platformRepository.findByCode("NES")).thenReturn(Optional.of(nes));

        gameService.save(marioDtoRequest());

        assertThat(nes.getGames())
                .allMatch(game ->
                        game.getName().equals("Super Mario Bros.") && game.getPlatform().getCode().equals("NES")
                );
        verify(eventPublisher).publishEvent(any(GameSaveEvent.class));
    }

    @Test
    void shouldThrowExceptionWhenPlatformNotFoundOnGameSave() {
        when(platformRepository.findByCode("NES")).thenThrow(new PlatformNotFoundException("NES"));

        GameDtoRequest mario = marioDtoRequest();
        assertThatThrownBy(() -> gameService.save(mario))
                .isInstanceOf(PlatformNotFoundException.class)
                .hasMessageContaining("NES");

    }
}