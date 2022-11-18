package com.epam.gameservice.aspect;

import org.aspectj.lang.JoinPoint;

public interface AspectParametersRetriever<T> {

    T retrieve(JoinPoint jp);
}