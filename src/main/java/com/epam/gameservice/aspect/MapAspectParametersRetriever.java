package com.epam.gameservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Component
public class MapAspectParametersRetriever implements AspectParametersRetriever<Map<String, Object>> {

    @Override
    public Map<String, Object> retrieve(JoinPoint jp) {
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
}