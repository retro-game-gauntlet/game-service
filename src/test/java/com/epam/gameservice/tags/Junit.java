package com.epam.gameservice.tags;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Tag("junit")
@Target(TYPE)
@Retention(RUNTIME)
public @interface Junit {
}
