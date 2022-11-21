package com.epam.gameservice.utils.formatter;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.join;

@Component
public class MapWithoutBracketsStringFormatter<K, V> implements StringFormatter<Map<K, V>> {

    @Override
    public String format(Map<K, V> map) {
        return map.entrySet().stream()
                .map(entry -> join("=", entry.getKey().toString(), entry.getValue().toString()))
                .collect(Collectors.joining(", "));
    }
}