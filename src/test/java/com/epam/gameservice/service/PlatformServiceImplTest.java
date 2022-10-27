package com.epam.gameservice.service;

import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.entity.Platform;
import com.epam.gameservice.exception.PlatformNotFoundException;
import com.epam.gameservice.repository.GameRepository;
import com.epam.gameservice.repository.PlatformRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlatformServiceImplTest {

    @InjectMocks
    private PlatformServiceImpl platformService;

    @Mock
    private PlatformRepository platformRepository;
    @Mock
    private GameRepository gameRepository;

    @Test
    void shouldCreatePlatformDtoByCode() {
        Platform nes = Platform.builder().code("NES").build();
        when(platformRepository.findByCode("nes")).thenReturn(Optional.of(nes));
        when(gameRepository.countByPlatformCode("NES")).thenReturn(2L);

        PlatformDto result = platformService.findByCode("nes");

        PlatformDto expected = new PlatformDto("NES", null, 2, null);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenPlatformDtoWasNotFondByCode() {
        assertThatThrownBy(() -> platformService.findByCode("qwe"))
                .isInstanceOf(PlatformNotFoundException.class)
                .hasMessageContaining("qwe");
    }
}