package com.epam.gameservice.controller.dto.platforms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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