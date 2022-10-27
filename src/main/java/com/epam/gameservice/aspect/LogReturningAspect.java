package com.epam.gameservice.aspect;

import com.epam.gameservice.annotation.LogReturning;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogReturningAspect {

    @Pointcut("@annotation(com.epam.gameservice.annotation.LogReturning)")
    public void anyMethodAnnotatedWithLogReturning() {
        // pointcut
    }

    /**
     * Log message from LogReturning annotation.
     * First variable in message is returned value.
     * Next variables in message are method arguments.
     */
    @AfterReturning(value = "anyMethodAnnotatedWithLogReturning()", returning = "retVal")
    public void logReturningValue(JoinPoint jp, Object retVal) {
        String message = getLoggingMessage(jp);
        Logger log = getLogger(jp);
        log.info(message, retVal, jp.getArgs());
    }

    private Logger getLogger(JoinPoint jp) {
        Class<?> aClass = jp.getTarget().getClass();
        return LoggerFactory.getLogger(aClass);
    }

    private String getLoggingMessage(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        LogReturning logReturning = method.getAnnotation(LogReturning.class);
        return logReturning.message();
    }
}
