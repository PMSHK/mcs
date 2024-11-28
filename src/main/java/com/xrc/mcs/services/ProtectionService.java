package com.xrc.mcs.services;

import com.xrc.mcs.calculators.formuls.Regression;
import com.xrc.mcs.dto.MaterialDto;
import com.xrc.mcs.dto.ResultLeadEquivalentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.xrc.mcs.calculators.formuls.Interpolator.interpolate;
import static com.xrc.mcs.calculators.formuls.NumberFormatter.roundNumber;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProtectionService {
    @Value(value = "${data.number_places}")
    private int precision;
    @Value(value = "${data.low_ren_limit}")
    private double lowRenLimit;
    @Value(value = "${data.not_demanded_protection}")
    private String notDemanded;

    private final ProtectionUtilService protectionUtilService;
    private final Regression regression;

    public List<String> getAllMaterials() {
        return protectionUtilService.getAllMaterials();
    }

    public double getMaterialLeadEquivalent(MaterialDto dto) {
        List<MaterialDto> list = protectionUtilService.getMaterialParamsOnVoltage(dto);
        double leadEquivalent = 0;
        if (list.isEmpty()) {
            return leadEquivalent;
        }

        if (list.get(list.size() - 1).getThickness() < dto.getThickness()) {
            log.info("there is no such big thickness: {} for {}", dto.getThickness(), dto.getName());
            log.info("thickness will be regressed");
            Double[] lowerThickness = list.stream().map(MaterialDto::getThickness).toArray(Double[]::new);
            Double[] lowerLeadEquivalent = list.stream().map(MaterialDto::getLeadEquivalent).toArray(Double[]::new);
            return roundNumber(regression.solve(dto.getThickness(), lowerThickness, lowerLeadEquivalent), precision);
        }
        MaterialDto[] lowHighRangeMaterials = protectionUtilService.getLowerHigherRangeMaterials(dto.getThickness(), list);
        log.info("lower thickness: {}, higher thickness: {}, recent thickness: {}", lowHighRangeMaterials[0], lowHighRangeMaterials[0], dto.getThickness());
        double result = interpolate(lowHighRangeMaterials[0].getThickness(), lowHighRangeMaterials[0].getLeadEquivalent(),
                lowHighRangeMaterials[1].getThickness(), lowHighRangeMaterials[1].getLeadEquivalent(),
                dto.getThickness());
        return roundNumber(result, precision);
    }

    public String getAdditionalProtection(ResultLeadEquivalentDto dto) {

        if (dto.getCalculatedLeadEquivalent() <= lowRenLimit &&
                dto.getExistedLeadEquivalent() <= lowRenLimit) {
            return String.valueOf(roundNumber(lowRenLimit - dto.getExistedLeadEquivalent(),precision));
        }
        double leadEquivalent = dto.getCalculatedLeadEquivalent() - dto.getExistedLeadEquivalent();
        if (leadEquivalent <= 0) {
            return notDemanded;
        }

        return String.valueOf(leadEquivalent);
    }

}
