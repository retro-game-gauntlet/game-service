package com.epam.gameservice.aspect.log;

import com.epam.gameservice.annotation.MethodLog;
import com.epam.gameservice.aspect.AspectParametersRetriever;
import com.epam.gameservice.utils.formatter.Formatter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InputMethodLogger implements MethodLogger {

    private final Formatter<Map<String, Object>> formatter;
    private final AspectParametersRetriever<Map<String, Object>> aspectParametersRetriever;
    private final AspectLoggerLookup aspectLoggerLookup;

    @Override
    public void log(Method method, JoinPoint jp, Object retVal) {
        MethodLog methodLog = method.getAnnotation(MethodLog.class);
        if (methodLog.logInput()) {
            Map<String, Object> args = aspectParametersRetriever.retrieve(jp);
            String parameters = formatter.format(args);
            Logger logger = aspectLoggerLookup.lookup(jp);
            logger.info("Method: '{}' was called with parameters: {}", method.getName(), parameters);
        }
    }
}