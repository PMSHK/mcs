package com.xrc.mcs.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultLeadEquivalentDto {
    @NotNull
    @Positive(message = "Значение требуемой защиты не должно быть меньше 0")
    private Double calculatedLeadEquivalent;
    @NotNull
    @Positive(message = "Значение существующей защиты не должно быть меньше 0")
    private Double existedLeadEquivalent;
}
