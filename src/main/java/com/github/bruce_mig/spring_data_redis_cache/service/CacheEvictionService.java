package com.github.bruce_mig.spring_data_redis_cache.service;

import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheEvictionService {

    private final CacheManager cacheManager;

    public CacheEvictionService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Scheduled(cron = "0 0 9 * * MON-FRI") // Evict all caches at 9 AM on weekdays
    public void evictAllCachesAt9AM() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            var cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        });
    }

}
