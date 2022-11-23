package com.epam.gameservice.web.dto.error;

import com.epam.gameservice.web.dto.GenericResponse;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ErrorResponse extends GenericResponse<ErrorDtoData> {
}