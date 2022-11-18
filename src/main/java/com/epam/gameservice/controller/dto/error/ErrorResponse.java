package com.epam.gameservice.controller.dto.error;

import com.epam.gameservice.controller.dto.GenericResponse;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ErrorResponse extends GenericResponse<ErrorDtoData> {
}