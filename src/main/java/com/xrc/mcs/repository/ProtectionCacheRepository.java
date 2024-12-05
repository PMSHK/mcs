package com.xrc.mcs.repository;

import com.xrc.mcs.calculators.converters.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    public <E,F> Map<E,List<F>> getMapFromCache(String key, Class<E> keyClass, Class<F> valueClass) {
        try {
            String json = redisTemplate.opsForValue().get(key);
            Map<E, List<F>> obj = jsonConverter.fromJson(json, keyClass, valueClass);
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

    public <T> List<T> getListFromCache(String key, Class<T> valueClass) {
        try {
            String json = redisTemplate.opsForValue().get(key);
            List<T> obj = jsonConverter.listFromJson(json, valueClass);
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
