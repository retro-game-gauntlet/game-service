package com.epam.gameservice.event.listeners;

import com.epam.gameservice.annotation.InputMethodLog;
import com.epam.gameservice.event.events.GameSaveEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static com.epam.gameservice.cache.CacheName.GAMES;

@Component
@RequiredArgsConstructor
public class InvalidateGameCacheListener implements ApplicationListener<GameSaveEvent> {

    private final CacheManager cacheManager;

    @Override
    @InputMethodLog
    public void onApplicationEvent(GameSaveEvent event) {
        Cache cache = cacheManager.getCache(GAMES);
        if (cache != null) {
            cache.invalidate();
        }
    }
}