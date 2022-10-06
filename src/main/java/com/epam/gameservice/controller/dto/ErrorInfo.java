package com.epam.gameservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorInfo {

    @JsonProperty("url")
    private final String url;
    @JsonProperty("message")
    private final String message;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.message = ex.getLocalizedMessage();
    }
}
