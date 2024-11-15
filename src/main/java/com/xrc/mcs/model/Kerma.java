package com.xrc.mcs.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.xrc.mcs.calculators.formuls.Interpolator.interpolate;

@Data
@Slf4j
public class Kerma {
    private Map<Double, Double> kermaStorage;

    public Kerma() {
        if (kermaStorage == null) {
            kermaStorage = new LinkedHashMap<>();
        }
        kermaStorage.put(40d, 2.0);
        kermaStorage.put(50d, 3.0);
        kermaStorage.put(70d, 5.6);
        kermaStorage.put(75d, 6.3);
        kermaStorage.put(100d, 9.0);
        kermaStorage.put(150d, 18.0);
        kermaStorage.put(200d, 25.0);
        kermaStorage.put(250d, 20.0);
        log.info("kerma storage: {} initialized", kermaStorage);
    }

    public double getKerma(double voltage) {
        double[] voltageRange = getVoltageRange(voltage);
        return interpolate(voltageRange[0], kermaStorage.get(voltageRange[0]), voltageRange[1], kermaStorage.get(voltageRange[1]), voltageRange[2]);
    }

    private double[] getVoltageRange(double voltage) {
        double[] range = new double[3];
        if (voltage <= 40) {
            range[0] = 40;
            range[1] = 50;
            range[2] = 40;
            log.info("voltage was lower then {} and was changed to minimal {}kV", range[0], range[0]);
            return range;
        } else if (voltage == 250) {
            range[0] = 200;
            range[1] = 250;
            range[2] = 250;
            log.info("voltage reached the maximal voltage: {}kV", range[1]);
            return range;
        } else if (voltage > 250) {
            log.info("voltage increased the maximal voltage: {}kV ", 250);
            throw new IllegalArgumentException("Напряжение должно лежать в диапазоне 40-250кВ");
        }
        double minVoltage = 40;
        double maxVoltage = 250;
        for (Map.Entry<Double, Double> entry : kermaStorage.entrySet()) {
            if (entry.getKey() <= voltage) {
                minVoltage = entry.getKey();
            }
            if (entry.getKey() > voltage) {
                maxVoltage = entry.getKey();
                break;
            }
        }
        range[0] = minVoltage;
        range[1] = maxVoltage;
        range[2] = voltage;
        log.info("got range {}kV - {}kV for voltage {}kV ", range[0], range[1], range[2]);
        return range;
    }
}
