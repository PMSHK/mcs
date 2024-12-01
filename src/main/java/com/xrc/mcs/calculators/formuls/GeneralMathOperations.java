package com.xrc.mcs.calculators.formuls;

public class GeneralMathOperations {
    public static <T extends Number> int getIntegerRelativeToLimit(T value, T limit) {
        double reminder = value.doubleValue() % limit.doubleValue();
        int result = 0;
        if (reminder != 0) {
            return (int) (value.doubleValue() / limit.doubleValue() + 1);
        }
        return (int) (value.doubleValue() / limit.doubleValue());
    }

    public static <T extends Number> double getValueRelativeToStep(T value, T step) {
        double reminder = value.doubleValue() % step.doubleValue();
        if (reminder != 0) {
            return (int) (value.doubleValue() / step.doubleValue()) * step.doubleValue() + step.doubleValue();
        }
        return value.doubleValue();
    }

    public static <T extends Number> int getPrecision(double limit) {
        String value = String.valueOf(limit);
        value = value.substring(value.indexOf(".") + 1);
        if (value.isEmpty() || value.equals("0")) {
            return 0;
        }
        return value.length();
    }
}
