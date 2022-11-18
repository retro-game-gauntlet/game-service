package com.epam.gameservice.aspect.log;

import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;

public interface MethodLogger {

    void log(Method method, JoinPoint jp, Object retVal);
}