package com.xrc.mcs.repository;

import com.xrc.mcs.calculators.converters.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProtectionCacheRepository {
    private final RedisTemplate<String, String> redisTemplate;
    private final JsonConverter jsonConverter;

    public void saveToCache(String key, Object value) {
        try {
            String json = jsonConverter.toJson(value);
            redisTemplate.opsForValue().set(key, json);
            log.info("Object {} was saved successfully in redis cache {}", json, key);
        } catch (RedisConnectionFailureException ex) {
            log.error("Redis connection failure", ex);
        }
    }

    public <T> T getFromCache(String key, Class<T> clazz) {
        try {
            String json = redisTemplate.opsForValue().get(key);
            T obj = jsonConverter.fromJson(json, clazz);
            if (json == null) {
                return null;
            }
            log.info("Object {} was gotten successfully from redis cache {}", obj.getClass().getSimpleName(), key);
            return obj;
        } catch (RedisConnectionFailureException ex) {
            log.error("Redis connection failure", ex);
            return null;
        }
    }
}
