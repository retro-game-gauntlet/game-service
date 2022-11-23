package com.epam.gameservice.exception;

import static java.lang.String.format;

public class CacheNotFoundException extends RuntimeException {

    public CacheNotFoundException(String cacheName) {
        super(format("Cache with name %s not found!", cacheName));
    }
}