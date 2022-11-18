package com.epam.gameservice.controller.jsonreader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlatformJsonReader {

    private final String fileName;
    private final JsonReader reader = new JsonReader();

    public String read() {
        return reader.read("/platform", fileName);
    }
}