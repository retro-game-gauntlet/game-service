package com.epam.gameservice.dao.repository;

import com.epam.gameservice.business.domain.GameDto;
import com.epam.gameservice.tags.Spring;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.epam.gameservice.factories.GameDtoFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

@Spring
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

        assertThat(games).containsExactly(earthwormJimDto());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Super Mario Bros.", "SUPER MARIO BROS."})
    void shouldFindGameByName(String name) {
        Optional<GameDto> game = gameRepository.findGameByName(name);

        assertThat(game)
                .map(GameDto::name)
                .hasValue("Super Mario Bros.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"nes", "NES"})
    void shouldRandomGameByPlatformCode(String platformCode) {
        Set<String> names = new HashSet<>();

        for (int i = 0; i < 50; i++) {
            String name = gameRepository.findRandomGameNameByPlatformCode(platformCode);
            names.add(name);
        }

        assertThat(names).containsExactlyInAnyOrder("Super Mario Bros.", "Battle City");
    }
}