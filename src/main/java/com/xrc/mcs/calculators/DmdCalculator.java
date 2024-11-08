package com.xrc.mcs.calculators;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.DmdParamDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DmdCalculator implements Computable<Double, DmdParamDto> {
    private DmdParamDto param;

    @Override
    public Double compute(DmdParamDto dmdParamDto) {
        return (Math.pow(10, 3) * param.getPd().getValue()) / (param.getT() * param.getTc() * param.getN());
    }
}
