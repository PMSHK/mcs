package com.xrc.mcs.services;

import com.xrc.mcs.dto.RadiationTypeDto;
import com.xrc.mcs.entity.RadiationType;
import com.xrc.mcs.enums.RadiationTypes;
import com.xrc.mcs.mapper.RadiationTypeMapper;
import com.xrc.mcs.repository.RadiationTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RadiationTypeService {
    private final RadiationTypeRepository radiationTypeRepository;
    private final RadiationTypeMapper mapper;

    public List<String> getAllRadiationTypes(RadiationTypes type) {
        return radiationTypeRepository.getAllNamesByTypes(type);
    }

    public RadiationTypeDto getRadiationTypeInfo(String name, RadiationTypes type) {
        return mapper.toDto(radiationTypeRepository.getRadiationParamsByTypeAndName(type, name));
    }
}
