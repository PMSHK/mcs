package com.xrc.mcs.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PD {
    A(20),
    B(5),
    C(1);
    final int value;
}
