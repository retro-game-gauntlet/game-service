package com.epam.gameservice.business.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class AspectMethodLookup {

    public Method lookup(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        return signature.getMethod();
    }
}