package com.epam.gameservice.web.dto.games;

import com.epam.gameservice.business.domain.GameDto;
import com.epam.gameservice.web.dto.GenericData;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class GamesDtoData extends GenericData<List<GameDto>> {
}