package com.epam.gameservice.exception;

import static java.lang.String.format;

public class PlatformNotFoundException extends RuntimeException {

    public PlatformNotFoundException(String platformCode) {
        super(format("Platform with code %s not found!", platformCode));
    }
}