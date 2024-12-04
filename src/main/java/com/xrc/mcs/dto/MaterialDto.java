package com.xrc.mcs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialDto implements Serializable {
    @JsonProperty("name")
    @Length(min = 1, max = 255, message = "Имя должно быть не более 256 символов и не менее 1")
    private String name;
    @JsonProperty("density")
    @Positive (message = "Значение не может быть меньше 0")
    private double density;
    @JsonProperty("thickness")
    @Nullable
//    @Positive (message = "Значение не может быть меньше 0")
    private double thickness;
    @JsonProperty("leadEquivalent")
    @Nullable
//    @Positive (message = "Значение не может быть меньше 0")
    private double leadEquivalent;
    @Nullable
    @Positive (message = "Напряжение не может быть меньше 0кВ")
    @Min(value = 50, message = "Напряжение не может быть меньше 50кВ")
    @Max(value = 250, message = "Напряжение не может быть больше 250кВ")
    private double voltage;
    @Nullable
    @Builder.Default
    private double limit = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialDto that = (MaterialDto) o;
        return Double.compare(density, that.density) == 0 && Double.compare(thickness, that.thickness) == 0 && Double.compare(leadEquivalent, that.leadEquivalent) == 0 && Double.compare(voltage, that.voltage) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, density, thickness, leadEquivalent, voltage);
    }
}
