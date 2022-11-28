package com.epam.gameservice.web.dto;

import com.epam.gameservice.web.dto.error.ErrorInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@RequiredArgsConstructor
@JsonInclude(NON_NULL)
@ToString
public class Response<T> {

    @JsonProperty("data")
    private final Data<T> data;

    @JsonProperty("errors")
    private final List<ErrorInfo> errorInfos;

    @JsonProperty("warnings")
    private final List<ErrorInfo> warningInfos;
}
