package com.epam.gameservice.aspect;

import com.epam.gameservice.annotation.MethodLog;
import com.epam.gameservice.utils.formatter.Formatter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;


@Aspect
@Component
@RequiredArgsConstructor
public class MethodLogAspect {

    private final Formatter<Map<String, Object>> formatter;
    private final AspectParametersRetriever<Map<String, Object>> aspectParametersRetriever;

    @Pointcut("@annotation(com.epam.gameservice.annotation.MethodLog)")
    public void anyMethodAnnotatedWithMethodLog() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithMethodLog()", returning = "retVal")
    public void logReturningValue(JoinPoint jp, Object retVal) {
        Method method = method(jp);
        MethodLog methodLog = method.getAnnotation(MethodLog.class);
        Map<String, Object> args = aspectParametersRetriever.retrieve(jp);
        if (methodLog.logInput()) {
            String parameters = formatter.format(args);
            logger(jp).info("Method: '{}' was called with parameters: {}",
                    method.getName(), parameters);
        }
        if (methodLog.logOutput()) {
            logger(jp).info("Method: '{}' returned: {}",
                    method.getName(), retVal);
        }
    }

    private Method method(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        return signature.getMethod();
    }

    private Logger logger(JoinPoint jp) {
        Class<?> aClass = jp.getTarget().getClass();
        return LoggerFactory.getLogger(aClass);
    }
}
