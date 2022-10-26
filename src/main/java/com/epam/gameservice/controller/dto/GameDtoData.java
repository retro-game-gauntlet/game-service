package com.epam.gameservice.controller.dto;

import com.epam.gameservice.domain.GameDto;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class GameDtoData extends GenericData<List<GameDto>> {
}