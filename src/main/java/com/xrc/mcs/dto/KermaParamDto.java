package com.xrc.mcs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KermaParamDto extends CalculatorDto{
    @Max(value = 250, message = "Максимальное расчётное напряжение 250кВ")
    @Min(value = 40, message = "Минимальное расчётное напряжение 40кВ")
    private double voltage;
}
