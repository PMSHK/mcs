package com.xrc.mcs.services;

import com.xrc.mcs.dto.RadiationTypeDto;
import com.xrc.mcs.enums.RadiationTypes;
import com.xrc.mcs.mapper.RadiationTypeMapper;
import com.xrc.mcs.repository.RadiationTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RadiationTypeService {
    private final RadiationTypeRepository radiationTypeRepository;
    private final RadiationTypeMapper mapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Cacheable(value = "radiationTypes")
    public List<String> getAllRadiationTypes(RadiationTypes type) {
        log.info("Got radiation types from DB");
        return radiationTypeRepository.getAllNamesByTypes(type);
    }

    @Cacheable(value = "radiationTypeInfo",cacheManager = "cacheManager")
    public RadiationTypeDto getRadiationTypeInfo(String name, RadiationTypes type) {
        log.info("Got details of radiation type {} from DB", name);
        return mapper.toDto(radiationTypeRepository.getRadiationParamsByTypeAndName(type, name));
    }
}
