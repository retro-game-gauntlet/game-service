package com.epam.gameservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@Getter
@JsonInclude(NON_NULL)
public class PlatformResponse {

    @JsonProperty("data")
    private final PlatformDtoData data;

    @JsonProperty("errors")
    private final List<ErrorInfo> errorInfos;
}