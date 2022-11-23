package com.epam.gameservice.factories;

import com.epam.gameservice.business.domain.GameDto;

import java.time.LocalDate;

public class GameDtoFactory {

    public static GameDto marioDto() {
        return new GameDto("Super Mario Bros.", LocalDate.of(1985, 9, 13));
    }

    public static GameDto earthwormJimDto() {
        return new GameDto("Earthworm Jim", LocalDate.of(1994, 1, 1));
    }
}
