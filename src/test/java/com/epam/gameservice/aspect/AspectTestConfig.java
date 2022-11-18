package com.epam.gameservice.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan({"com.epam.gameservice.aspect", "com.epam.gameservice.utils.formatter"})
@Configuration
@EnableAspectJAutoProxy
public class AspectTestConfig {
}