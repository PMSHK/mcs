package com.xrc.mcs.calculators;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.KParamDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Setter
@Valid
@Slf4j
public class KCalculator implements Computable<Double, KParamDto> {
    private final Computable<Double, Double> kermaCalculator;

    @Override
    public Double compute(KParamDto param) {
        return (Math.pow(10, 3) * calculateK(param.getVoltage()) * param.getWorkLoad() * param.getDirectionCoefficient()) / (30 * Math.pow(param.getDistance(), 2) * param.getDmd());
    }

    private Double calculateK(Double voltage) {
        log.info("Calculated kerma for voltage: {} ", voltage);
        return kermaCalculator.compute(voltage);
    }

}
