package com.epam.gameservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Builder
@Getter
@JsonInclude(NON_NULL)
public class PlatformResponse {

    @JsonProperty("data")
    private PlatformDtoData data;

    @JsonProperty("errors")
    private List<ErrorInfo> errorInfos;
}