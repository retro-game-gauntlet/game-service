package com.epam.gameservice.factories;

import com.epam.gameservice.entity.Platform;

import java.time.LocalDate;

import static com.epam.gameservice.factories.GameFactory.mario;

public class PlatformFactory {

    public static Platform nes() {
        Platform nes = Platform.builder()
                .code("NES")
                .name("Nintendo Entertainment System")
                .releasedAt(LocalDate.of(1983, 6, 15))
                .build();
        nes.addGame(mario());
        return nes;
    }
}
