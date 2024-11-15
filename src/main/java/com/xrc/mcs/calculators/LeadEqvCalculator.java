package com.xrc.mcs.calculators;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.DmdParamDto;
import com.xrc.mcs.dto.LeadEqvParamDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Setter
public class LeadEqvCalculator implements Computable<Double, LeadEqvParamDto> {


    @Override
    public Class<LeadEqvParamDto> getParameterType() {
        return LeadEqvParamDto.class;
    }

    @Override
    public Double compute(LeadEqvParamDto dto) {
        // Нужно написать логику!
        return 0d;
    }
}
