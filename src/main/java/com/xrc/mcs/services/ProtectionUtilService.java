package com.xrc.mcs.services;

import com.xrc.mcs.calculators.formuls.Regression;
import com.xrc.mcs.dto.MaterialDto;
import com.xrc.mcs.mapper.MaterialMapper;
import com.xrc.mcs.repository.MaterialRepository;
import com.xrc.mcs.repository.MaterialThicknessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProtectionUtilService {
    private final MaterialRepository materialRepository;
    private final MaterialThicknessRepository materialThicknessRepository;
    private final MaterialMapper materialMapper;
    private final Regression regression;

    public List<String> getAllMaterials() {
        return materialRepository.findAll().stream().map(material -> material.getName() + ", " + material.getDensity()).toList();
    }

    public double getMaterialLeadEquivalent(MaterialDto dto) {
        log.info("started to calculate lead equivalent of {}", dto.getName());
        List<MaterialDto> materialDtoList = new ArrayList<>();
        Map<Double, List<MaterialDto>> matParametersList = getAllMatParamForVoltage(dto);
        if (matParametersList.isEmpty()) {
            log.info("no matParameters found");
            return 0.0;
        }
        if (matParametersList.containsKey(dto.getVoltage())) {
            materialDtoList = matParametersList.get(dto.getVoltage());
            
        }
        Set<Double> keys = matParametersList.keySet();
        List<MaterialDto> leftList = new ArrayList<>();
        List<MaterialDto> rightList = new ArrayList<>();
        Optional<Double> leftKey = keys.stream().filter(key -> key <= dto.getVoltage()).findFirst();
        Optional<Double> rightKey = keys.stream().filter(key -> key > dto.getVoltage()).findFirst();
        if (leftKey.isPresent() && rightKey.isPresent()) {
            leftList = matParametersList.get(leftKey.get());
            rightList = matParametersList.get(rightKey.get());
        } else {
            log.info("no matParameters found");
            return 0.0;
        }
        for(int i=0; i<leftList.size(); i++) {

        }
    }

    private double supposeThickness(double thickness, Double[] lowerThicknesses, Double[] lowerLeadEquivalents) {
        return regression.solve(thickness, lowerThicknesses, lowerLeadEquivalents);
    }

    private Map<Double, List<MaterialDto>> getAllMatParamForVoltage(MaterialDto dto) {
        List<Object[]> results = materialThicknessRepository.getAllMatThicknessesOnVoltage(dto.getName(), dto.getVoltage());
        log.info("got all parameters for material {} from database", dto.getName());
        return results.stream().map(result -> new MaterialDto(dto.getName(),
                        ((BigDecimal) result[0]).doubleValue(),     //density
                        ((BigDecimal) result[1]).doubleValue(),    //thickness
                        ((Double) result[2]),    //leadEquivalent
                        ((Double) result[3])    //voltage
                )).
                collect(Collectors.groupingBy(MaterialDto::getVoltage, Collectors.toList()));
    }
}
