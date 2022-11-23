package com.epam.gameservice.business.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan({"com.epam.gameservice.business.aspect", "com.epam.gameservice.business.utils.formatter"})
@Configuration
@EnableAspectJAutoProxy
public class AspectTestConfig {
}