package com.epam.gameservice.controller.dto.platforms;

import java.time.LocalDate;

public record PlatformDtoRequest(String code, String name, LocalDate releasedAt) {
}