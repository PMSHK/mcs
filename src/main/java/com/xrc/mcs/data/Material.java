package com.xrc.mcs.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    private String name;
    private double thickness;
    private double density;
    private double leadEqvl;
}
