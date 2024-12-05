package com.xrc.mcs.services;

import com.xrc.mcs.calculators.formuls.Regression;
import com.xrc.mcs.dto.MaterialDto;
import com.xrc.mcs.dto.MaterialInfoDto;
import com.xrc.mcs.mapper.MaterialMapper;
import com.xrc.mcs.repository.MaterialRepository;
import com.xrc.mcs.repository.MaterialThicknessRepository;
import com.xrc.mcs.repository.ProtectionCacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.xrc.mcs.calculators.formuls.Interpolator.interpolate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProtectionUtilService {
    private final MaterialRepository materialRepository;
    private final MaterialThicknessRepository materialThicknessRepository;
    private final MaterialMapper materialMapper;
    private final Regression regression;
    private final ProtectionCacheRepository pcRepository;

    public List<MaterialInfoDto> getAllMaterials() {
        List<MaterialInfoDto> materialList = pcRepository.getFromCache("materials", ArrayList.class);
        if (materialList == null) {
            log.info("get all materials from DB");
            materialList = materialRepository.findAll().stream().map(material -> new MaterialInfoDto(material.getName(), material.getDensity())).toList();
            pcRepository.saveToCache("materials", materialList);
        }
        return materialList;
    }

    public MaterialDto[] getLowerHigherRangeMaterials(double thickness, List<MaterialDto> list) {
        int lowPtr = 0;
        int highPtr = list.size() - 1;
        MaterialDto lowDto = new MaterialDto();
        MaterialDto highDto = new MaterialDto();
        while (lowPtr <= highPtr) {
            if (list.get(lowPtr).getThickness() <= thickness) {
                lowDto = list.get(lowPtr);
                lowPtr++;
            }
            if (list.get(highPtr).getThickness() > thickness) {
                highDto = list.get(highPtr);
                highPtr--;
            }
        }
        return new MaterialDto[]{lowDto, highDto};
    }

    public List<MaterialDto> getMaterialParamsOnVoltage(MaterialDto dto) {
        List<MaterialDto> materialDtoList = pcRepository.getListFromCache(dto.getName() + " params: " + dto.getVoltage() + dto.getThickness() + dto.getLeadEquivalent(), MaterialDto.class);
        if (materialDtoList == null) {
            log.info("get {} params from DB", dto.getName());
            log.info("started to calculate lead equivalent of {}", dto.getName());
            materialDtoList = new ArrayList<>();
            Map<Double, List<MaterialDto>> matParametersList = getAllMatParamForVoltage(dto);
            if (matParametersList.isEmpty()) {
                log.info("Parameters for {} was not found", dto.getName());
                return Collections.emptyList();
            }
            if (matParametersList.containsKey(dto.getVoltage())) {
                materialDtoList = matParametersList.get(dto.getVoltage());
                log.info("{} params found for recent voltage {}", dto.getName(), dto.getVoltage());
                return materialDtoList;
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
                log.info("Parameters for {} was not found", dto.getName());
                return Collections.emptyList();
            }
            for (int i = 0; i < leftList.size(); i++) {
                MaterialDto materialDto = new MaterialDto(dto.getName(), dto.getDensity(),
                        interpolate(leftList.get(i).getVoltage(), leftList.get(i).getThickness(), rightList.get(i).getVoltage(), rightList.get(i).getThickness(), dto.getVoltage()),
                        leftList.get(i).getLeadEquivalent(), dto.getVoltage(), dto.getLimit());
                log.info("materialDto: {} was initialized for voltage {}", materialDto, dto.getVoltage());
                materialDtoList.add(materialDto);
                log.info("materialDtoList got a new materialDto {} for voltage {}", materialDto, dto.getVoltage());
            }
            pcRepository.saveToCache(dto.getName() + " params: " + dto.getVoltage() + dto.getThickness() + dto.getLeadEquivalent(), materialDtoList);
        }

        return materialDtoList;
    }

    private Map<Double, List<MaterialDto>> getAllMatParamForVoltage(MaterialDto dto) {
        Map<Double, List<MaterialDto>> matParametersList = pcRepository.getMapFromCache("MatParams: " + dto.getName() + dto.getVoltage() + dto.getThickness() + dto.getLeadEquivalent(), Double.class, MaterialDto.class);
        if (matParametersList == null) {
            List<Object[]> results = new ArrayList<>();
            if (dto.getThickness() != 0) {
                results = materialThicknessRepository.getAllMatThicknessesOnVoltage(dto.getName(), dto.getVoltage());
                log.info("got list of parameters of material {} on voltage from DB", dto.getName());
            }
            if (dto.getLeadEquivalent() != 0) {
                log.info("got list of parameters of material {} on voltage from DB", dto.getName());
                results = materialThicknessRepository.getAllMatThicknessesOnVoltage(dto.getName(), dto.getVoltage(), dto.getLeadEquivalent());
            }
            log.info("got all parameters for material {} from database", dto.getName());
            matParametersList = results.stream().map(result -> new MaterialDto(dto.getName(),
                            ((BigDecimal) result[0]).doubleValue(),     //density
                            ((BigDecimal) result[1]).doubleValue(),    //thickness
                            ((BigDecimal) result[2]).doubleValue(),    //leadEquivalent
                            ((Long) result[3]),    //voltage
                            dto.getLimit()
                    )).
                    collect(Collectors.groupingBy(MaterialDto::getVoltage, Collectors.toList()));
            pcRepository.saveToCache("MatParams: " + dto.getName() + dto.getVoltage() + dto.getThickness() + dto.getLeadEquivalent(), matParametersList);
        }
        return matParametersList;

    }
}
