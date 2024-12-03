package com.xrc.mcs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RadiationTypeDto implements Serializable {
    @Length(min = 1, max = 1000)
    private String name;
    @Max(value = 250, message = "Максимальное расчётное напряжение 250кВ")
    @Min(value = 40, message = "Минимальное расчётное напряжение 40кВ")
    private long voltage;
    private long radiationExit;
}
