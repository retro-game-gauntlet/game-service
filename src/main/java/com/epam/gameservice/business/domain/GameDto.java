package com.epam.gameservice.business.domain;

import java.time.LocalDate;

public record GameDto(String name, LocalDate releasedAt) {
}