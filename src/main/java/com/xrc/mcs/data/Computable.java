package com.xrc.mcs.data;

public interface Computable<T,E> {
    T compute(E val);
}
