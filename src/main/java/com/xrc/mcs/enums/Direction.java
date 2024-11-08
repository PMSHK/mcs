package com.xrc.mcs.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public enum Direction implements Serializable {
    DIRECT(1),
    SCATTER(0.05),
    SEMI_SCATTER(0.1);
    private final double directionValue;
}
