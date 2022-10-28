package com.epam.gameservice.factories;

import com.epam.gameservice.entity.Game;

import java.time.LocalDate;

public class GameFactory {

    public static Game mario() {
        return Game.builder()
                .name("Super Mario Bros.")
                .releasedAt(LocalDate.of(1985, 9, 13))
                .build();
    }
}
