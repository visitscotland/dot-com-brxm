package com.visitscotland.brxm.config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Caffeine;


@Configuration
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        cacheManager.setCaches(List.of(
                // Default cache settings
                buildCache("default", 10_000, 120, 120),

                // Product caches
                buildCache("dmsProduct", 10_000, 3600),
                buildCache("dmsProductSearch", 10_000, 3600),

                // Other caches
                buildCache("youtube", 1_000, 86400),
                buildCache("externalDocument", 1_000, 1814400),
                buildCache("navigation", 36, 1800)
        ));

        return cacheManager;
    }

    private Cache buildCache(
            String name, long maximumSize, long ttlSeconds) {

        return new CaffeineCache(
                name,
                Caffeine.newBuilder()
                        .maximumSize(maximumSize)
                        .expireAfterWrite(ttlSeconds, TimeUnit.SECONDS)
                        .build()
        );
    }

    private Cache buildCache(
            String name,
            long maximumSize,
            long ttlSeconds,
            long ttiSeconds) {

        return new CaffeineCache(
                name,
                Caffeine.newBuilder()
                        .maximumSize(maximumSize)
                        .expireAfterWrite(ttlSeconds, TimeUnit.SECONDS)
                        .expireAfterAccess(ttiSeconds, TimeUnit.SECONDS)
                        .build()
        );
    }
}
