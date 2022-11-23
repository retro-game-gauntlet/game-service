package com.epam.gameservice.business.event.listeners;

import com.epam.gameservice.business.annotation.InputMethodLog;
import com.epam.gameservice.business.cache.InvalidateCache;
import com.epam.gameservice.business.event.events.PlatformSaveEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static com.epam.gameservice.business.cache.CacheName.PLATFORMS;

@Component
@RequiredArgsConstructor
public class InvalidatePlatformCacheOnPlatformSaveEventListener implements ApplicationListener<PlatformSaveEvent> {

    private final InvalidateCache invalidateCache;

    @Override
    @InputMethodLog
    public void onApplicationEvent(@NonNull PlatformSaveEvent event) {
        invalidateCache.invalidate(PLATFORMS);
    }
}