package com.xrc.mcs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RadiationTypeDto {
    @Length(min = 1, max = 1000)
    private String name;
    private long voltage;
    private long radiationExit;
}
