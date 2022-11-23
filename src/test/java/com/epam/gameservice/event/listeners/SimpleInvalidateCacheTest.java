package com.epam.gameservice.event.listeners;

import com.epam.gameservice.cache.SimpleInvalidateCache;
import com.epam.gameservice.exception.CacheNotFoundException;
import com.epam.gameservice.tags.Junit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Junit
@ExtendWith(MockitoExtension.class)
class SimpleInvalidateCacheTest {

    @InjectMocks
    private SimpleInvalidateCache invalidateCache;

    @Mock
    private CacheManager cacheManager;
    @Mock
    private Cache cache;

    @Test
    void shouldInvalidateCache() {
        when(cacheManager.getCache("games")).thenReturn(cache);

        invalidateCache.invalidate("games");

        verify(cache).invalidate();
    }

    @Test
    void shouldThrowExceptionWhenCacheNotFound() {
        when(cacheManager.getCache("qwe")).thenReturn(null);

        assertThatThrownBy(() -> invalidateCache.invalidate("qwe"))
                .isInstanceOf(CacheNotFoundException.class)
                .hasMessageContaining("qwe");
    }
}