package com.epam.gameservice.factories;

import com.epam.gameservice.controller.dto.games.GameDtoRequest;

import java.time.LocalDate;

public class GameDtoRequestFactory {

    public static GameDtoRequest marioDtoRequest() {
        return new GameDtoRequest("Super Mario Bros.",
                LocalDate.of(1985, 9, 13),
                "NES");
    }
}
