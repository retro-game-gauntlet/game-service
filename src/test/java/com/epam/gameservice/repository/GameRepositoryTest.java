package com.epam.gameservice.repository;

import com.epam.gameservice.domain.GameDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql({"classpath:sql/platforms.sql", "classpath:sql/games.sql"})
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @ParameterizedTest
    @ValueSource(strings = {"nes", "NES"})
    void shouldCountGamesByPlatformCode(String platformCode) {
        long count = gameRepository.countByPlatformCode(platformCode);

        assertThat(count).isEqualTo(2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"smd", "SMD"})
    void shouldFindGamesByPlatformCode(String platformCode) {
        List<GameDto> games = gameRepository.findGamesByPlatformCode(platformCode);

        GameDto earthwormJim = new GameDto("Earthworm Jim", LocalDate.of(1994, 1, 1));
        assertThat(games).containsExactly(earthwormJim);
    }
}