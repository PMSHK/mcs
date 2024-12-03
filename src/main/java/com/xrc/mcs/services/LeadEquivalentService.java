package com.xrc.mcs.services;

import com.xrc.mcs.dto.LeadEquivalentParamDto;
import com.xrc.mcs.repository.LeadEquivalentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import static com.xrc.mcs.calculators.formuls.Interpolator.interpolate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadEquivalentService {
    @Value(value = "${data.max_voltage}")
    private double maxVoltage;
    @Value(value = "${data.min_voltage}")
    private double minVoltage;
    @Value(value = "${data.max_attenuation_frequency}")
    private double maxAttenuationFrequency;
    @Value(value = "${data.min_attenuation_frequency}")
    private double minAttenuationFrequency;
    @Value(value = "${data.doubleFormat}")
    private String doubleFormat;
    private final LeadEquivalentRepository leadEquivalentRepository;

    public double getLeadEquivalentThickness(double voltage, double attenuationFrequency) {
        List<LeadEquivalentParamDto> list = getLeadParameters(voltage, attenuationFrequency);
        double v1 = list.get(0).getVoltage();
        double v2 = list.get(2).getVoltage();
        double a1 = list.get(0).getAttenuationFrequency();
        double a2 = list.get(1).getAttenuationFrequency();
        double th1 = interpolate(a2, list.get(1).getThickness(), a1, list.get(0).getThickness(), attenuationFrequency);
        double th2 = interpolate(a2, list.get(2).getThickness(), a1, list.get(3).getThickness(), attenuationFrequency);

        return Double.parseDouble(new DecimalFormat(doubleFormat).format(interpolate(v1, th1, v2, th2, voltage)));
    }

    @Cacheable(value = "leadParameters", key = "#voltage + #attenuationFrequency")
    public List<LeadEquivalentParamDto> getLeadParameters(double voltage, double attenuationFrequency) {
        if (attenuationFrequency >= maxAttenuationFrequency) {
            attenuationFrequency = (maxAttenuationFrequency - 0.1);
        }
        if (voltage == maxVoltage) {
            voltage = (maxVoltage - 0.1);
        }
        if (voltage < minVoltage && voltage >= 40) {
            voltage = (minVoltage);
        }
        List<Object[]> results = leadEquivalentRepository.getLeadParametersNative(voltage, attenuationFrequency);
        return results.stream()
                .map(result -> new LeadEquivalentParamDto(
                        ((Long) result[0]), // voltage
                        ((Long) result[1]), // attenuationFrequency
                        ((BigDecimal) result[2]).floatValue()  // thickness
                )).toList();
    }
}
