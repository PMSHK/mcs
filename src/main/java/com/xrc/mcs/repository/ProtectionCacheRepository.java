package com.xrc.mcs.repository;

import com.xrc.mcs.calculators.converters.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

    public <E, F> Map<E, List<F>> getMapFromCache(String key, Class<E> keyClass, Class<F> valueClass) {
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

    public <T> void updateAnElementFromList(String key, Class<T> clazz, Predicate<T> filter, Consumer<T> action) {
        List<T> list = getListFromCache(key, clazz);
        boolean updated = false;
        AtomicReference<T> element = new AtomicReference<>();
        if (list == null) {
            return;
        } else {
            updated = list.stream().filter(filter).findFirst().map(obj -> {
                action.accept(obj);
                element.set(obj);
                return true;
            }).orElse(false);
        }
        if (updated) {
            saveToCache(key, list);
            log.info("Object {} was updated successfully in redis cache {}", element.getClass().getSimpleName(), key);
        }
    }

    public <V> void deleteFromCache(String key, Class<V> valueClass, Predicate<V> filter) {
        List<V> list = getListFromCache(key, valueClass);
        if (list == null) {
            return;
        } else {
            list.removeIf(filter);
            saveToCache(key, list);
            log.info("Object {} was deleted successfully in redis cache {}", list.getClass().getSimpleName(), key);
        }
//        Map<K, List<V>> map = getMapFromCache(key, keyClass, valueClass);
//        if (map == null) {
//            log.warn("Object {} was not found in redis cache {} and can not be deleted", keyClass.getSimpleName(), keyClass);
//        } else {
//            for (Map.Entry<K, List<V>> m : map.entrySet()) {
//                List<V> list = m.getValue();
//                if (list != null) {
//                    for (V v : list) {
//                        action.accept(v);
//                    }
//                }
//            }
//        }
    }
}

