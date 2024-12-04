package com.xrc.mcs.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {
    @Value(value = "${spring.data.redis.host}")
    private String host;
    @Value(value = "${spring.data.redis.port}")
    private int port;
    @Value(value = "${spring.data.redis.ttl}")
    private String ttl;
    private final ObjectMapper mcsObjectMapper;


    @Bean
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory factory,GenericJackson2JsonRedisSerializer serializer) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(host, port));
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfig(GenericJackson2JsonRedisSerializer serializer) {
        return RedisCacheConfiguration.defaultCacheConfig().
                entryTtl(Duration.parse(ttl)).disableCachingNullValues().
                enableTimeToIdle().
                serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
    }

    @Primary
    @Bean
    public CacheManager cacheManager(JedisConnectionFactory factory, RedisCacheConfiguration redisCacheConfig) {
        Map<String, RedisCacheConfiguration> configs = new HashMap<>();

        return RedisCacheManager.builder(factory)
                .cacheDefaults(redisCacheConfig)
                .build();
    }

    @Bean
    public GenericJackson2JsonRedisSerializer serializer() {
//        objectMapper .activateDefaultTyping(
//                LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_FINAL
//        );
//        objectMapper.findAndRegisterModules();
        return new GenericJackson2JsonRedisSerializer(mcsObjectMapper);
    }
}
