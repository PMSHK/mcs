package com.xrc.mcs.calculators;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.KermaParamDto;
import com.xrc.mcs.model.Kerma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KermaCalculator implements Computable<Double, KermaParamDto> {
    private final Kerma kerma;

    @Autowired
    public KermaCalculator(Kerma kerma) {
        this.kerma = kerma;
    }

    @Override
    public Double compute(KermaParamDto dto) {
        return kerma.getKerma(dto.getVoltage());
    }

    @Override
    public Class<KermaParamDto> getParameterType() {
        return KermaParamDto.class;
    }
}
