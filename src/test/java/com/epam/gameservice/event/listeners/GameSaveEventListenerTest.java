package com.epam.gameservice.event.listeners;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameSaveEventListenerTest {

    @InjectMocks
    private GameSaveEventListener gameSaveEventListener;

    @Mock
    private CacheManager cacheManager;
    @Mock
    private Cache cache;

    @Test
    void shouldCallInvalidate() {
        when(cacheManager.getCache("games")).thenReturn(cache);

        gameSaveEventListener.onApplicationEvent(any());

        verify(cache).invalidate();
    }

    @Test
    void shouldNotCallInvalidateWhenCacheNotFound() {
        when(cacheManager.getCache("games")).thenReturn(null);

        gameSaveEventListener.onApplicationEvent(any());

        verify(cache, never()).invalidate();
    }
}