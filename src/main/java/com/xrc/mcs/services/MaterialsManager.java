package com.xrc.mcs.services;

import com.xrc.mcs.dto.MaterialInfoDto;
import com.xrc.mcs.dto.PairMaterialInfoDto;
import com.xrc.mcs.entity.Material;
import com.xrc.mcs.entity.MaterialThickness;
import com.xrc.mcs.repository.MaterialRepository;
import com.xrc.mcs.repository.MaterialThicknessRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialsManager {
    private final MaterialRepository materialRepository;
    private final MaterialThicknessRepository thicknessRepository;

    @Transactional()
    public void addMaterialBasedOnMaterial(MaterialInfoDto childDto, MaterialInfoDto parentDto) {
        Material material = materialRepository.findByNameAndDensityWithThicknesses(parentDto.getName(), parentDto.getDensity()).orElseThrow(() -> new EntityNotFoundException(parentDto.getName() + " was not found"));
        Material copyMaterial = new Material();
        copyMaterial.setName(childDto.getName());
        copyMaterial.setDensity(childDto.getDensity());
        materialRepository.save(copyMaterial);
        List<MaterialThickness> thicknessesStorage = material.getMaterialThicknessList()
                .stream()
                .map(parent -> {
                    MaterialThickness thickness = new MaterialThickness();
                    thickness.setMaterial(copyMaterial);
                    thickness.setVoltage(parent.getVoltage());
                    thickness.setMaterialLeadEquivalent(parent.getMaterialLeadEquivalent());
                    thickness.setThickness(parent.getThickness() * (parent.getMaterial().getDensity() / copyMaterial.getDensity()));
                    return thickness;
                }).toList();
        thicknessRepository.saveAll(thicknessesStorage);
    }

    @Transactional
    public void deleteMaterial(MaterialInfoDto dto) {
        Material material = materialRepository.findByNameAndDensityWithThicknesses(dto.getName(), dto.getDensity()).orElseThrow(() -> new EntityNotFoundException(dto.getName() + " was not found"));
        materialRepository.delete(material);
    }

    @Transactional
    public void updateMaterial(PairMaterialInfoDto dto) {
        Material material = materialRepository.findByNameAndDensityWithThicknesses(dto.getSourceMaterial().getName(), dto.getSourceMaterial().getDensity()).orElseThrow(() -> new EntityNotFoundException(dto.getSourceMaterial().getName() + " was not found"));
        if (!dto.getTargetMaterial().getDensity().equals(dto.getSourceMaterial().getDensity())) {
            material.setDensity(dto.getTargetMaterial().getDensity());
            material.getMaterialThicknessList().
                    forEach(materialThickness -> materialThickness.setThickness(material.getDensity()*materialThickness.getThickness()/dto.getTargetMaterial().getDensity()));
        }
        if (StringUtils.hasText(dto.getTargetMaterial().getMaterialName()) && !dto.getTargetMaterial().getMaterialName().equals(dto.getSourceMaterial().getName())) {
            material.setName(dto.getTargetMaterial().getMaterialName());
        }
        materialRepository.save(material);
    }

}
