package com.xrc.mcs.dto;

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
public class KParamDto {
    @NotNull (message = "Рабочая нагрузка должна присутствовать")
    @Positive(message = "Рабочая нагрузка не может быть отрицательной")
    private double workLoad;
    @NotNull (message = "Коэффициент направленности должен присутствовать")
    @Positive(message = "Коэффициент направленности не может быть отрицательным")
    @Builder.Default
    private double directionCoefficient = DIRECT.getDirectionValue();
    @NotNull (message = "Расстояние должно присутствовать")
    @Positive(message = "Расстояние не может быть отрицательным")
    private double distance;
    @NotNull (message = "ДМД должна присутствовать")
    @Positive(message = "ДМД не может быть отрицательной")
    private double dmd;
    @NotNull (message = "Анодное напряжение должно присутствовать")
    @Positive(message = "Анодное напряжение не может быть отрицательным")
    private double voltage;
}
