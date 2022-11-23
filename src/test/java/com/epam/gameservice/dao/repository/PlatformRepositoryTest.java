package com.epam.gameservice.dao.repository;

import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.dao.entity.Platform;
import com.epam.gameservice.tags.Spring;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static com.epam.gameservice.factories.PlatformDtoFactory.nesDto;
import static org.assertj.core.api.Assertions.assertThat;

@Spring
@DataJpaTest
@Sql({"classpath:sql/platforms.sql", "classpath:sql/games.sql"})
class PlatformRepositoryTest {

    @Autowired
    private PlatformRepository platformRepository;

    @ParameterizedTest
    @ValueSource(strings = {"nes", "NES"})
    void shouldFindPlatformByCode(String code) {
        Optional<Platform> nes = platformRepository.findByCode(code);

        assertThat(nes)
                .map(Platform::getCode)
                .hasValue("NES");
    }

    @ParameterizedTest
    @ValueSource(strings = {"nes", "NES"})
    void shouldFindPlatformDtoByCode(String code) {
        Optional<PlatformDto> nes = platformRepository.findPlatformDtoByCode(code);

        assertThat(nes)
                .get()
                .isEqualTo(nesDto());
    }
}