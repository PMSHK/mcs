package com.xrc.mcs.services;

import com.xrc.mcs.dto.MaterialInfoDto;
import com.xrc.mcs.entity.Material;
import com.xrc.mcs.entity.MaterialThickness;
import com.xrc.mcs.repository.MaterialRepository;
import com.xrc.mcs.repository.MaterialThicknessRepository;
import com.xrc.mcs.repository.ProtectionCacheRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialsManager {
    private final MaterialRepository materialRepository;
    private final MaterialThicknessRepository thicknessRepository;
    private final ProtectionService protectionService;
    private final ProtectionCacheRepository pcRepository;

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
        List<MaterialInfoDto> materials = protectionService.getAllMaterials();
        MaterialInfoDto materialDto = new MaterialInfoDto(copyMaterial.getName(), copyMaterial.getDensity());
        materials.add(materialDto);

        pcRepository.saveToCache("materials", materials);
    }

    @Transactional
    public void deleteMaterial(MaterialInfoDto dto) {
        Material material = materialRepository.findByNameAndDensityWithThicknesses(dto.getName(), dto.getDensity()).orElseThrow(() -> new EntityNotFoundException(dto.getName() + " was not found"));
        materialRepository.delete(material);
        updateDelMaterialInCache(
                materials -> {
                    materials.removeIf(mat -> mat.getName().equals(dto.getName()) && mat.getDensity().equals(dto.getDensity()));
                });
    }

    @Transactional
    public void updateMaterial(MaterialInfoDto dto, String matName, float matDensity) {
        Material material = materialRepository.findByNameAndDensityWithThicknesses(matName, matDensity).orElseThrow(() -> new EntityNotFoundException(dto.getName() + " " + dto.getDensity() + " was not found"));
        boolean isUpdated = false;

        if (!dto.getDensity().equals(material.getDensity())) {
            material.setDensity(dto.getDensity());
            material.getMaterialThicknessList().forEach(materialThickness -> materialThickness.setThickness(matDensity * materialThickness.getThickness() / dto.getDensity()));
            thicknessRepository.saveAll(material.getMaterialThicknessList());
            isUpdated = true;
        }
        if (StringUtils.hasText(dto.getMaterialName()) && !dto.getName().equals(material.getName())) {
            material.setName(dto.getMaterialName());
            isUpdated = true;
        }
        if (isUpdated) {
            materialRepository.save(material);
            pcRepository.updateAnElementFromList("materials", MaterialInfoDto.class,
                    mat ->
                            mat.getName().equals(matName) && mat.getDensity() == matDensity,
                    mat -> {
                        mat.setName(dto.getName());
                        mat.setDensity(dto.getDensity());

                    }
            );

//            updateMaterialCache(matName,matDensity,dto);
//            updateDelMaterialInCache(
//                    materials -> {
//                        materials.stream().filter(mat -> mat!=null &&
//                                        mat.getName().equals(matName) &&
//                                                mat.getDensity()==matDensity)
//                                .findFirst()
//                                .ifPresent(mat -> {
//                                    mat.setName(dto.getName());
//                                    mat.setDensity(dto.getDensity());
//                                });
//                    });
        }

    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void updateMaterialCache(String matName, float matDensity, MaterialInfoDto dto) {
//        updateDelMaterialInCache(materials -> {
//            materials.stream().filter(mat -> mat!=null &&
//                            mat.getName().equals(matName) &&
//                            Math.abs(mat.getDensity()-matDensity)< 0.01f)
//                    .findFirst()
//                    .ifPresent(mat -> {
//                        mat.setName(dto.getName());
//                        mat.setDensity(dto.getDensity());
//                    });
//        });
//    }

    private void updateDelMaterialInCache(Consumer<List<MaterialInfoDto>> consumer) {
        List<MaterialInfoDto> materials = protectionService.getAllMaterials();
        log.debug("materials from cache within updating: {}", materials);
        consumer.accept(materials);
        pcRepository.saveToCache("materials", materials);
    }

}
