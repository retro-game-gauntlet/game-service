package com.epam.gameservice.aspect.log;

import com.epam.gameservice.aspect.AspectMethodLookup;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class MethodLogAspect {

    private final AspectMethodLookup aspectMethodLookup;
    private final List<MethodLogger> methodLoggers;

    @Pointcut("@annotation(com.epam.gameservice.annotation.MethodLog)")
    public void anyMethodAnnotatedWithMethodLog() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithMethodLog()", returning = "retVal")
    public void logMethod(JoinPoint jp, Object retVal) {
        Method method = aspectMethodLookup.lookup(jp);
        methodLoggers.forEach(logger -> logger.log(method, jp, retVal));
    }
}