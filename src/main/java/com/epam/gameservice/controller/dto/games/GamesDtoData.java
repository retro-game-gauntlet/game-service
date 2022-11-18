package com.epam.gameservice.controller.dto.games;

import com.epam.gameservice.controller.dto.GenericData;
import com.epam.gameservice.domain.GameDto;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class GamesDtoData extends GenericData<List<GameDto>> {
}