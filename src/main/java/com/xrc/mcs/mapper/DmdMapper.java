package com.xrc.mcs.mapper;

import com.xrc.mcs.dto.DmdDto;
import com.xrc.mcs.entity.Dmd;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface DmdMapper {
    Dmd toEntity(DmdDto dto);
    DmdDto toDto(Dmd dmd);
}
