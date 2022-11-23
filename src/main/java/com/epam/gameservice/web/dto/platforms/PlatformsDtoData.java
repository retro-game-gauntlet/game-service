package com.epam.gameservice.web.dto.platforms;

import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.web.dto.GenericData;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class PlatformsDtoData extends GenericData<List<PlatformDto>> {
}