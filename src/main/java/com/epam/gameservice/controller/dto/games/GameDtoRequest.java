package com.epam.gameservice.controller.dto.games;

import java.time.LocalDate;

public record GameDtoRequest(String name, LocalDate releasedAt, String platformCode) {
}
