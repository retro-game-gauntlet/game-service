package com.epam.gameservice.business.aspect.log;

import com.epam.gameservice.business.aspect.AspectMethodLookup;
import com.epam.gameservice.business.aspect.AspectMethodParametersRetriever;
import com.epam.gameservice.business.utils.formatter.StringFormatter;
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
    private final AspectMethodParametersRetriever aspectMethodParametersRetriever;
    private final StringFormatter<Map<String, Object>> stringFormatter;
    private final AspectLoggerLookup aspectLoggerLookup;

    @Pointcut("@annotation(com.epam.gameservice.business.annotation.InputMethodLog)")
    public void anyMethodAnnotatedWithInputMethodLog() {
        // pointcut
    }

    @Before(value = "anyMethodAnnotatedWithInputMethodLog()")
    public void logMethod(JoinPoint jp) {
        Map<String, Object> args = aspectMethodParametersRetriever.retrieve(jp);
        String parameters = stringFormatter.format(args);
        Method method = aspectMethodLookup.lookup(jp);
        Logger logger = aspectLoggerLookup.lookup(jp);
        logger.info("Method: '{}' was called with parameters: {}", method.getName(), parameters);
    }
}