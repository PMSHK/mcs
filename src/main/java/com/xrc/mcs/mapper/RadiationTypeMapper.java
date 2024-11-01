package com.xrc.mcs.mapper;

import com.xrc.mcs.dto.RadiationTypeDto;
import com.xrc.mcs.entity.RadiationType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RadiationTypeMapper {
    RadiationType toEntity(RadiationTypeDto dto);

    RadiationTypeDto toDto(RadiationType entity);
}
