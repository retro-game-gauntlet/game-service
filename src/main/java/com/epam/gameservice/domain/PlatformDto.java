package com.epam.gameservice.domain;

import java.time.LocalDate;

public record PlatformDto(String code, String name, long gamesCount, LocalDate releasedAt) {
}