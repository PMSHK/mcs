package com.xrc.mcs.calculators.formuls;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.regression.SimpleRegression;

@Slf4j
public class Regression {
    public <T extends Number> double solve(T x, T[] xValues, T[] yValues) {
        if (xValues.length != yValues.length) {
            log.error("xValues.length != yValues.length within solving a regression");
            throw new IllegalArgumentException("xValues.length != yValues.length");
        }
        SimpleRegression sr = new SimpleRegression();

        for (int i = 0; i < xValues.length; i++) {
            sr.addData(xValues[i].doubleValue(), yValues[i].doubleValue());
        }
        return sr.predict(x.doubleValue());
    }
}
