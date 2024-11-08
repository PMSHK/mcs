package com.xrc.mcs.data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdjustmentRoom {
    @NotNull
    @NotEmpty(message = "Наименование стены должно быть указано")
    private String wallName;
    @NotNull
    @NotEmpty(message = "Требуется указать назначение помещения")
    private String roomName;
    @NotNull
    @NotEmpty(message = "Требуется указать категорию облучаемых лиц")
    private String category;
    @Positive(message = "Значение ДМД не может быть отрицательным")
    private long dmd;
}
