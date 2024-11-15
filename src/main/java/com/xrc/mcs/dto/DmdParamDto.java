package com.xrc.mcs.dto;

import com.xrc.mcs.enums.PD;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmdParamDto extends CalculatorDto{
    private int t;
    private double p;
    private double tc;
    private double n;
    private PD pd;
}
