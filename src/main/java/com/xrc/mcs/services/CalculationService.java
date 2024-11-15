package com.xrc.mcs.services;


import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.CalculatorDto;
import com.xrc.mcs.factory.CalculatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationService {
    private final CalculatorFactory calculatorFactory;

    public <T, E extends CalculatorDto> T calculate(E dto) {
        Computable<T, E> calculator = calculatorFactory.getCalculator(dto);
        return calculator.compute(dto);
    }

}
