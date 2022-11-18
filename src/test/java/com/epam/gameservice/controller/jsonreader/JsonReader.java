package com.epam.gameservice.controller.jsonreader;

import lombok.SneakyThrows;

import java.io.File;

import static java.lang.String.format;
import static java.nio.file.Files.readAllBytes;
import static org.springframework.util.ResourceUtils.getFile;

class JsonReader {

    @SneakyThrows
    String read(String packageName, String fileName) {
        String resourceLocation = format("classpath:json/%s/%s", packageName, fileName);
        File file = getFile(resourceLocation);
        byte[] bytes = readAllBytes(file.toPath());
        return new String(bytes);
    }
}