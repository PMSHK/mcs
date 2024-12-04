package com.xrc.mcs.calculators.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonConverter {
    private final ObjectMapper objectMapper;

    public <T> String toJson(T obj) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            log.info("object: {} was successfully converted to json: {}", obj, json);
            return json;
        } catch (JsonProcessingException e) {
            log.error("the issue was occurred while converting object {} to json", obj, e);
            throw new RuntimeException(e);
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            if (json == null || json.isEmpty()) {
                return null;
            }
            T obj = objectMapper.readValue(json, clazz);
            log.info("object: {} was successfully converted from json: {}", obj, json);
            return obj;
        } catch (JsonProcessingException e) {
            log.error("the issue was occurred while converting object from json {}", json, e);
            throw new RuntimeException(e);
        }
    }

}
