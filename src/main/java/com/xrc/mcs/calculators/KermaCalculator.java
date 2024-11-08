package com.xrc.mcs.calculators;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.repository.KermaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KermaCalculator implements Computable<Double, Double> {
    private final KermaRepository kermaRepository;
    private double voltage;

    @Override
    public Double compute(Double voltage) {
        double kerma = kermaRepository.getKerma(voltage);
        return;
    }
}
