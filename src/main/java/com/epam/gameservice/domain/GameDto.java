package com.epam.gameservice.domain;

import java.time.LocalDate;

public record GameDto(String name, LocalDate releasedAt) {
}