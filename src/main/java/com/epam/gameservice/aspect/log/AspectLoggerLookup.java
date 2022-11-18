package com.epam.gameservice.aspect.log;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AspectLoggerLookup {

    public Logger lookup(JoinPoint jp) {
        Class<?> aClass = jp.getTarget().getClass();
        return LoggerFactory.getLogger(aClass);
    }
}