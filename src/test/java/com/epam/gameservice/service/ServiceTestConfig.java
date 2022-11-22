package com.epam.gameservice.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ComponentScan({"com.epam.gameservice.service"})
@Configuration
@EnableCaching
public class ServiceTestConfig {

    @Bean
    public ConcurrentMapCache gamesCache() {
        return new ConcurrentMapCache("games");
    }

    @Bean
    public ConcurrentMapCache platformsCache() {
        return new ConcurrentMapCache("platforms");
    }

    @Bean
    public CacheManager cacheManager(ConcurrentMapCache gamesCache, ConcurrentMapCache platformsCache) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(gamesCache, platformsCache));
        cacheManager.initializeCaches();
        return cacheManager;
    }
}