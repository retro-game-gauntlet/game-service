package com.epam.gameservice.controller.dto;

import com.epam.gameservice.domain.PlatformDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlatformDtoData {

    @JsonProperty("attributes")
    private final PlatformDto platformDto;
}