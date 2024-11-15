package com.xrc.mcs.configuration;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.CalculatorDto;
import com.xrc.mcs.enums.Direction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor

public class CalculatorsConfig {
    private final List<Computable<?, ? extends CalculatorDto>> computables;

    @Bean(name = "calculatorsMap")
    public Map<String, Computable<?, ? extends CalculatorDto>> calculators() {
        Map<String, Computable<?, ? extends CalculatorDto>> map = new HashMap<>();
        for (Computable<?, ? extends CalculatorDto> computable : computables) {
            map.put(computable.getParameterType().getSimpleName(), computable);
        }
        return map;
    }

    @Bean
    public Direction directions(){
        return Direction.DIRECT;
    }
}
