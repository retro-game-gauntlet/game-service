package com.epam.gameservice.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@RequiredArgsConstructor
@JsonInclude(NON_NULL)
@ToString
public class Data<T> {

    @JsonProperty("attributes")
    private final T attributes;
}
