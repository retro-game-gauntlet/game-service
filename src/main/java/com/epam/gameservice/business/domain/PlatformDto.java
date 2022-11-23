package com.epam.gameservice.business.domain;

import java.time.LocalDate;

public record PlatformDto(String code, String name, long gamesCount, LocalDate releasedAt) {
}