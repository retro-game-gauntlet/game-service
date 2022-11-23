package com.epam.gameservice.factories;

import com.epam.gameservice.dao.entity.Platform;

import java.time.LocalDate;

public class PlatformFactory {

    public static Platform nes() {
        return Platform.builder()
                .code("NES")
                .name("Nintendo Entertainment System")
                .releasedAt(LocalDate.of(1983, 6, 15))
                .build();
    }
}
