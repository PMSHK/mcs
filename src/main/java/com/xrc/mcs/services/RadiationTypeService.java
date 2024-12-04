package com.xrc.mcs.services;

import com.xrc.mcs.dto.RadiationTypeDto;
import com.xrc.mcs.enums.RadiationTypes;
import com.xrc.mcs.mapper.RadiationTypeMapper;
import com.xrc.mcs.repository.ProtectionCacheRepository;
import com.xrc.mcs.repository.RadiationTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RadiationTypeService {
    private final RadiationTypeRepository radiationTypeRepository;
    private final RadiationTypeMapper mapper;
    private final ProtectionCacheRepository pcRepository;

    public List<String> getAllRadiationTypes(RadiationTypes type) {
        List<String> typeslList = pcRepository.getFromCache("radiationTypes", ArrayList.class);
        if (typeslList == null) {
            log.info("Got radiation types from DB");
            typeslList = radiationTypeRepository.getAllNamesByTypes(type);
            pcRepository.saveToCache("radiationTypes", typeslList);
        }
        return typeslList;
    }

    public RadiationTypeDto getRadiationTypeInfo(String name, RadiationTypes type) {
        RadiationTypeDto radiationTypeDto = pcRepository.getFromCache(type + ": radiationTypeInfo: " + name, RadiationTypeDto.class);
        if (radiationTypeDto == null) {
            log.info("Got details of radiation type {} from DB", name);
            radiationTypeDto = mapper.toDto(radiationTypeRepository.getRadiationParamsByTypeAndName(type, name));
            pcRepository.saveToCache(type + ": radiationTypeInfo: " + name, radiationTypeDto);
        }
        return radiationTypeDto;
    }
}
