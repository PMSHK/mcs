package com.xrc.mcs.factory;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.CalculatorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;

@Component
@Slf4j
public class CalculatorFactory {
    private final Map<String, Computable<?, ? extends CalculatorDto>> calculatorsMap;

    @Autowired
    public CalculatorFactory(@Qualifier(value = "calculatorsMap") Map<String, Computable<?, ? extends CalculatorDto>> calculatorsMap) {
        this.calculatorsMap = calculatorsMap;
    }

    public <T, E extends CalculatorDto> Computable<T, E> getCalculator(E dto) {
        Computable<T, E> calculator = (Computable<T, E>) calculatorsMap.get(dto.getClass().getSimpleName());
        if (calculator == null) {
            log.warn("Did not find calculator {}", dto);
            throw new NoSuchElementException("Did not find calculator " + dto);
        }
        log.info("Found calculator {}", dto);
        return calculator;
    }
}
