package com.epam.gameservice.web.jsonreader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameJsonReader {

    private final String fileName;
    private final JsonReader reader = new JsonReader();

    public String read() {
        return reader.read("/game", fileName);
    }
}