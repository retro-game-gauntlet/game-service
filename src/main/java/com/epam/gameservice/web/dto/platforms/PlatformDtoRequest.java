package com.epam.gameservice.web.dto.platforms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record PlatformDtoRequest(
        @NotBlank(message = "Code is mandatory")
        String code,
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotNull(message = "Released date is mandatory")
        @PastOrPresent(message = "Released date can't be in future")
        LocalDate releasedAt) {
}