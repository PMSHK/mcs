package com.xrc.mcs.mapper;

import com.xrc.mcs.dto.MaterialDto;
import com.xrc.mcs.entity.Material;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface MaterialMapper {
    Material toEntity(MaterialDto dto);
    MaterialDto toDto(Material entity);
}
