package com.epam.gameservice.business.exception;

import static java.lang.String.format;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String name) {
        super(format("Game with name %s not found!", name));
    }
}
