package com.xrc.mcs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeadEquivalentParamDto {
    @Max(value = 250, message = "Максимальное расчётное напряжение 250кВ")
    @Min(value = 40, message = "Минимальное расчётное напряжение 40кВ")
    private long voltage;
    @Min(value = 1, message = "Минимальный коэффициент ослабления 1")
    @Max(value = 10000000, message = "Максимальный коэффициент ослабления 10 000 000")
    private long attenuationFrequency;
    private float thickness;
}
