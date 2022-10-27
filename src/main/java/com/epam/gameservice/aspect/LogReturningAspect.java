package com.epam.gameservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogReturningAspect {

    @Pointcut("@annotation(com.epam.gameservice.annotation.LogReturning)")
    public void anyMethodAnnotatedWithLogReturning() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithLogReturning()", returning = "retVal")
    public void logReturningValue(JoinPoint jp, Object retVal) {
        Method method = method(jp);
        Map<String, Object> args = methodArgs(jp);
        if (args.isEmpty()) {
            logger(jp).info("Method: {{}} returned: {}",
                    method.getName(), retVal);
        } else {
            logger(jp).info("Method: {{}} with parameters: {} returned: {}",
                    method.getName(), args, retVal);
        }
    }

    private Map<String, Object> methodArgs(JoinPoint jp) {
        Method method = method(jp);
        Parameter[] parameters = method.getParameters();
        Object[] args = jp.getArgs();
        Map<String, Object> result = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            result.put(parameters[i].getName(), args[i]);
        }
        return result;
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
