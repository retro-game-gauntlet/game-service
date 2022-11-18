package com.epam.gameservice.controller.dto;

import com.epam.gameservice.controller.dto.error.ErrorInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@SuperBuilder
@JsonInclude(NON_NULL)
public abstract class GenericResponse<T> {

    @JsonProperty("data")
    protected final T data;

    @JsonProperty("errors")
    protected final List<ErrorInfo> errorInfos;
}
