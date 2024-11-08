package com.xrc.mcs.services;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.KParamDto;
import com.xrc.mcs.dto.ProtectionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationService {

    public <T, E> T calc(Computable<T, E> calculator, E dto) {
        return calculator.compute(dto);
    }

    public ProtectionDto calcProtection(KParamDto dto) {
        this.computable = context.getBean("kCalculator", Computable.class);
        ;
        ProtectionDto protectionDto = new ProtectionDto();
        protectionDto.setWeaknessCoefficient(kCalculator.compute(dto));
        protectionDto.setLeadEqv(leadEqvCalculator(protectionDto.getWeaknessCoefficient()));
        return protectionDto;
    }
}
