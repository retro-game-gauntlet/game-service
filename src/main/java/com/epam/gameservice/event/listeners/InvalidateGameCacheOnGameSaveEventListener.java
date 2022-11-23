package com.epam.gameservice.event.listeners;

import com.epam.gameservice.annotation.InputMethodLog;
import com.epam.gameservice.cache.InvalidateCache;
import com.epam.gameservice.event.events.GameSaveEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static com.epam.gameservice.cache.CacheName.GAMES;

@Component
@RequiredArgsConstructor
public class InvalidateGameCacheOnGameSaveEventListener implements ApplicationListener<GameSaveEvent> {

    private final InvalidateCache invalidateCache;

    @Override
    @InputMethodLog
    public void onApplicationEvent(@NonNull GameSaveEvent event) {
        invalidateCache.invalidate(GAMES);
    }
}