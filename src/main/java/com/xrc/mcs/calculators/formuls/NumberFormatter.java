package com.xrc.mcs.calculators.formuls;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberFormatter {
    public static <T> double roundNumber(T value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(String.valueOf(value));
        return bd.setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
}
