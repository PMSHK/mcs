package com.xrc.mcs.dto;

import com.xrc.mcs.enums.RadiationTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RadTypeDto {
    @Builder.Default
    private RadiationTypes type = RadiationTypes.MED;
}
