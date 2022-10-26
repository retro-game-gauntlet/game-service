package com.epam.gameservice.controller.dto;

import com.epam.gameservice.domain.GameDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GameDtoData {

    @JsonProperty("attributes")
    private final List<GameDto> gameDtos;
}