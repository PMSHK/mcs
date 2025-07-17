package com.xrc.mcs.data.openings;

import com.xrc.mcs.data.Opening;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.xrc.mcs.calculators.formuls.GeneralMathOperations.getPrecision;
import static com.xrc.mcs.calculators.formuls.GeneralMathOperations.getValueRelativeToStep;
import static com.xrc.mcs.calculators.formuls.NumberFormatter.roundNumber;

@Component
@Setter
public class Door implements Opening {
    @Value(value = "${data.low_ren_limit}")
    private double lowLimit;
    private String leadProtectionLevel;
    @Getter
    private String name = "Дверь";

    @Override
    public String getLeadProtectionLevel(double calculatedLeadEquivalent, double precision) {
        return "Pb = " + getEquivalent(calculatedLeadEquivalent,precision) + " мм.";
    }

    private double getEquivalent(double value, double step) {
        if (value <= lowLimit) {
            return lowLimit;
        }
        return roundNumber(getValueRelativeToStep(value, step),getPrecision(step));
    }

}
