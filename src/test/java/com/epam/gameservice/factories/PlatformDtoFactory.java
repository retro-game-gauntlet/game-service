package com.epam.gameservice.factories;

import com.epam.gameservice.domain.PlatformDto;

import java.time.LocalDate;

public class PlatformDtoFactory {

    public static PlatformDto nesDto() {
        return new PlatformDto("NES",
                "Nintendo Entertainment System",
                2,
                LocalDate.of(1983, 6, 15));
    }
}