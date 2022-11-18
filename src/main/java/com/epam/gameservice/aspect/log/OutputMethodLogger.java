package com.epam.gameservice.aspect.log;

import com.epam.gameservice.annotation.MethodLog;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class OutputMethodLogger implements MethodLogger {

    private final AspectLoggerLookup aspectLoggerLookup;

    @Override
    public void log(Method method, JoinPoint jp, Object retVal) {
        MethodLog methodLog = method.getAnnotation(MethodLog.class);
        if (methodLog.logOutput()) {
            Logger logger = aspectLoggerLookup.lookup(jp);
            logger.info("Method: '{}' returned: {}", method.getName(), retVal);
        }
    }
}