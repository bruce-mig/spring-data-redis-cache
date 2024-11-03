package com.github.bruce_mig.spring_data_redis_cache.util;

import org.springframework.data.redis.cache.RedisCacheWriter.TtlFunction;
import org.springframework.lang.Nullable;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public enum CustomTtlFunction implements TtlFunction {
    INSTANCE;

    @Override
    public Duration getTimeToLive(Object key, @Nullable Object value) {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        LocalTime time = now.toLocalTime();

        // Weekend case: Saturday and Sunday
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return Duration.ofHours(48);
        }

        // Weekday cases: Monday to Friday
        if (time.isAfter(LocalTime.of(9, 0)) && time.isBefore(LocalTime.of(13, 0))) {
            return Duration.ofMinutes(5);
        } else if (time.isAfter(LocalTime.of(13, 0)) && time.isBefore(LocalTime.of(17, 0))) {
            return Duration.ofMinutes(30);
        } else {
            return Duration.ofHours(16); // From 17:00 to 9:00 the next day
        }
    }
}
