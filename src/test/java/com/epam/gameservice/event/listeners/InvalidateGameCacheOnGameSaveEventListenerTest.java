package com.epam.gameservice.event.listeners;

import com.epam.gameservice.cache.InvalidateCache;
import com.epam.gameservice.event.events.GameSaveEvent;
import com.epam.gameservice.tags.Junit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.epam.gameservice.cache.CacheName.GAMES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@Junit
@ExtendWith(MockitoExtension.class)
class InvalidateGameCacheOnGameSaveEventListenerTest {

    @InjectMocks
    private InvalidateGameCacheOnGameSaveEventListener invalidateGameCacheOnGameSaveEventListener;

    @Mock
    private InvalidateCache invalidateCache;

    @Test
    void shouldCallInvalidate() {
        invalidateGameCacheOnGameSaveEventListener.onApplicationEvent(any(GameSaveEvent.class));

        verify(invalidateCache).invalidate(GAMES);
    }
}