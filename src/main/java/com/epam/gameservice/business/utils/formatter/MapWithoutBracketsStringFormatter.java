package com.epam.gameservice.business.utils.formatter;

import org.springframework.stereotype.Component;

import java.util.Map;

import static java.lang.String.join;
import static java.util.stream.Collectors.joining;

@Component
public class MapWithoutBracketsStringFormatter<K, V> implements StringFormatter<Map<K, V>> {

    @Override
    public String format(Map<K, V> map) {
        return map.entrySet().stream()
                .map(entry -> join("=", entry.getKey().toString(), entry.getValue().toString()))
                .collect(joining(", "));
    }
}