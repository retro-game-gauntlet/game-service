package com.epam.gameservice.web.dto.error;

import com.epam.gameservice.web.dto.GenericData;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class ErrorDtoData extends GenericData<List<ErrorInfo>> {
}
