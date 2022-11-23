package com.epam.gameservice.factories;

import com.epam.gameservice.business.domain.PlatformDto;

import java.time.LocalDate;

public class PlatformDtoFactory {

    public static PlatformDto nesDto() {
        return new PlatformDto("NES",
                "Nintendo Entertainment System",
                2,
                LocalDate.of(1983, 6, 15));
    }

    public static PlatformDto smdDto() {
        return new PlatformDto("SMD",
                "Sega Mega Drive",
                1,
                LocalDate.of(1988, 10, 29));
    }
}
