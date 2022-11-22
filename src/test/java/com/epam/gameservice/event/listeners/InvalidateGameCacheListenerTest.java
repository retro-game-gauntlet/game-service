package com.epam.gameservice.event.listeners;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static com.epam.gameservice.cache.CacheName.GAMES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvalidateGameCacheListenerTest {

    @InjectMocks
    private InvalidateGameCacheListener invalidateGameCacheListener;

    @Mock
    private CacheManager cacheManager;
    @Mock
    private Cache cache;

    @Test
    void shouldCallInvalidate() {
        when(cacheManager.getCache(GAMES)).thenReturn(cache);

        invalidateGameCacheListener.onApplicationEvent(any());

        verify(cache).invalidate();
    }

    @Test
    void shouldNotCallInvalidateWhenCacheNotFound() {
        when(cacheManager.getCache(GAMES)).thenReturn(null);

        invalidateGameCacheListener.onApplicationEvent(any());

        verify(cache, never()).invalidate();
    }
}