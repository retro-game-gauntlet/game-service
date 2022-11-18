package com.epam.gameservice.controller.dto.error;

import com.epam.gameservice.controller.dto.GenericData;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class ErrorDtoData extends GenericData<List<ErrorInfo>> {
}
