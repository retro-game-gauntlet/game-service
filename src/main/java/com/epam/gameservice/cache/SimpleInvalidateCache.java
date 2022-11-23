package com.epam.gameservice.cache;

import com.epam.gameservice.annotation.InputMethodLog;
import com.epam.gameservice.exception.CacheNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleInvalidateCache implements InvalidateCache {

    private final CacheManager cacheManager;

    @Override
    @InputMethodLog
    public void invalidate(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);

        if (cache == null) {
            throw new CacheNotFoundException(cacheName);
        }

        cache.invalidate();
    }
}