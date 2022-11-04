package com.epam.gameservice.factories;

import com.epam.gameservice.controller.dto.platforms.PlatformDtoRequest;

import java.time.LocalDate;

public class PlatformDtoRequestFactory {

    public static PlatformDtoRequest nesDtoRequest() {
        return new PlatformDtoRequest("NES",
                "Nintendo Entertainment System",
                LocalDate.of(1983, 6, 15));
    }
}