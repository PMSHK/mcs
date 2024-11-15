package com.xrc.mcs.calculators;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.DmdParamDto;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Setter
public class DmdCalculator implements Computable<Double, DmdParamDto> {

    @Override
    public Class<DmdParamDto> getParameterType() {
        return DmdParamDto.class;
    }

    @Override
    public Double compute(DmdParamDto dto) {
        return (Math.pow(10, 3) * dto.getPd().getValue()) / (dto.getT() * dto.getTc() * dto.getN());
    }
}
