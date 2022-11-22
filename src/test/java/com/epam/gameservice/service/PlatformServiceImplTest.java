package com.epam.gameservice.service;

import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.entity.Platform;
import com.epam.gameservice.exception.PlatformNotFoundException;
import com.epam.gameservice.repository.PlatformRepository;
import com.epam.gameservice.tags.Junit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.epam.gameservice.factories.PlatformDtoFactory.nesDto;
import static com.epam.gameservice.factories.PlatformDtoRequestFactory.nesDtoRequest;
import static com.epam.gameservice.factories.PlatformFactory.nes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Junit
@ExtendWith(MockitoExtension.class)
class PlatformServiceImplTest {

    @InjectMocks
    private PlatformServiceImpl platformService;

    @Mock
    private PlatformRepository platformRepository;
    @Captor
    private ArgumentCaptor<Platform> platformArgumentCaptor;

    @Test
    void shouldFindPlatformDtoByCode() {
        when(platformRepository.findPlatformDtoByCode("nes")).thenReturn(Optional.of(nesDto()));

        PlatformDto result = platformService.findPlatformDtoByCode("nes");

        assertThat(result).isEqualTo(nesDto());
    }

    @Test
    void shouldThrowExceptionWhenPlatformDtoWasNotFondByCode() {
        assertThatThrownBy(() -> platformService.findPlatformDtoByCode("qwe"))
                .isInstanceOf(PlatformNotFoundException.class)
                .hasMessageContaining("qwe");
    }

    @Test
    void shouldFindPlatformByCode() {
        when(platformRepository.findByCode("nes")).thenReturn(Optional.of(nes()));

        Platform result = platformService.findByCode("nes");

        assertThat(result)
                .extracting(Platform::getCode)
                .isEqualTo("NES");
    }

    @Test
    void shouldThrowExceptionWhenPlatformWasNotFondByCode() {
        assertThatThrownBy(() -> platformService.findByCode("qwe"))
                .isInstanceOf(PlatformNotFoundException.class)
                .hasMessageContaining("qwe");
    }

    @Test
    void shouldSavePlatform() {
        platformService.save(nesDtoRequest());

        verify(platformRepository).save(platformArgumentCaptor.capture());
        Platform platform = platformArgumentCaptor.getValue();
        assertThat(platform.getCode()).isEqualTo(nes().getCode());
        assertThat(platform.getName()).isEqualTo(nes().getName());
    }
}