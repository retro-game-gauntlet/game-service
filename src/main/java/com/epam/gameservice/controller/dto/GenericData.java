package com.epam.gameservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@SuperBuilder
@JsonInclude(NON_NULL)
public abstract class GenericData<T> {

    @JsonProperty("attributes")
    protected final T attributes;
}
