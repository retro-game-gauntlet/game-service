package com.epam.gameservice.factories;

import com.epam.gameservice.entity.Platform;

import java.time.LocalDate;

import static com.epam.gameservice.factories.GameFactory.mario;
import static java.util.Collections.singleton;

public class PlatformFactory {

    public static Platform nes() {
        return Platform.builder()
                .code("NES")
                .name("Nintendo Entertainment System")
                .releasedAt(LocalDate.of(1983, 6, 15))
                .games(singleton(mario()))
                .build();
    }
}
