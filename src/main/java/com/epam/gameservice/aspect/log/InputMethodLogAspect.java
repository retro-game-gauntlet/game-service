package com.epam.gameservice.aspect.log;

import com.epam.gameservice.aspect.AspectMethodLookup;
import com.epam.gameservice.aspect.AspectParametersRetriever;
import com.epam.gameservice.utils.formatter.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class InputMethodLogAspect {

    private final AspectMethodLookup aspectMethodLookup;
    private final AspectParametersRetriever aspectParametersRetriever;
    private final StringFormatter<Map<String, Object>> stringFormatter;
    private final AspectLoggerLookup aspectLoggerLookup;

    @Pointcut("@annotation(com.epam.gameservice.annotation.InputMethodLog)")
    public void anyMethodAnnotatedWithInputMethodLog() {
        // pointcut
    }

    @Before(value = "anyMethodAnnotatedWithInputMethodLog()")
    public void logMethod(JoinPoint jp) {
        Map<String, Object> args = aspectParametersRetriever.retrieve(jp);
        String parameters = stringFormatter.format(args);
        Method method = aspectMethodLookup.lookup(jp);
        Logger logger = aspectLoggerLookup.lookup(jp);
        logger.info("Method: '{}' was called with parameters: {}", method.getName(), parameters);
    }
}