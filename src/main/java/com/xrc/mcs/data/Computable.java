package com.xrc.mcs.data;

import com.xrc.mcs.dto.CalculatorDto;

public interface Computable<T, E extends CalculatorDto> {
    T compute(E val);

    Class<E> getParameterType();
}
