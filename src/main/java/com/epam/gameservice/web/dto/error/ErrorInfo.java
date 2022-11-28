package com.epam.gameservice.web.dto.error;

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
public class ErrorInfo {

    @JsonProperty("url")
    private final String url;
    @JsonProperty("message")
    private final String message;
}
