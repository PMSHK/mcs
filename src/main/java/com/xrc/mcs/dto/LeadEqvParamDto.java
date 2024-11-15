package com.xrc.mcs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadEqvParamDto extends CalculatorDto{
    private String name;
    private Double thickness;
    private Double density;
}
