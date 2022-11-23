package com.epam.gameservice.web.dto.games;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public record GameDtoRequest(
        @NotNull(message = "Name is mandatory")
        String name,
        @NotNull(message = "Released date is mandatory")
        @PastOrPresent(message = "Released date can't be in future")
        LocalDate releasedAt,
        @NotNull(message = "Platform code is mandatory")
        String platformCode) {
}
