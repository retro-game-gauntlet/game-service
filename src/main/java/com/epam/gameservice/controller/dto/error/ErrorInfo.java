package com.epam.gameservice.controller.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorInfo {

    @JsonProperty("url")
    private final String url;
    @JsonProperty("message")
    private final String message;

    public ErrorInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }
}
