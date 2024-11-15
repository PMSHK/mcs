package com.xrc.mcs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.xrc.mcs.enums.Direction.DIRECT;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KParamDto extends CalculatorDto{
    @NotNull(message = "Рабочая нагрузка должна присутствовать")
    @Positive(message = "Рабочая нагрузка не может быть отрицательной")
    private double workLoad;
    @NotNull(message = "Коэффициент направленности должен присутствовать")
    @Positive(message = "Коэффициент направленности не может быть отрицательным")
    @Builder.Default
    private double directionCoefficient = DIRECT.getDirectionValue();
    @NotNull(message = "Расстояние должно присутствовать")
    @Positive(message = "Расстояние не может быть отрицательным")
    private double distance;
    @NotNull(message = "ДМД должна присутствовать")
    @Positive(message = "ДМД не может быть отрицательной")
    private double dmd;
    @NotNull(message = "Анодное напряжение должно присутствовать")
    @Positive(message = "Анодное напряжение не может быть отрицательным")
    @Max(value = 250, message = "Максимальное расчётное напряжение 250кВ")
    @Min(value = 40, message = "Минимальное расчётное напряжение 40кВ")
    private double voltage;
    @NotNull(message = "Керма должна быть рассчитана")
    @Positive(message = "Керма не может быть отрицательной")
    private double kerma;
}
