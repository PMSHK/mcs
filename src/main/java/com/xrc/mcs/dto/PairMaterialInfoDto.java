package com.xrc.mcs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PairMaterialInfoDto {
    private MaterialInfoDto targetMaterial;
    private MaterialInfoDto sourceMaterial;
}
