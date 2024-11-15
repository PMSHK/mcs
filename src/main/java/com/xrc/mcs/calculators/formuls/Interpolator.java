package com.xrc.mcs.calculators.formuls;

public class Interpolator {
    public static double interpolate(double x1, double y1,double x2, double y2, double value) {
        return y1 + (value - x1) * (y2 - y1) / (x2 - x1);
    }
}
