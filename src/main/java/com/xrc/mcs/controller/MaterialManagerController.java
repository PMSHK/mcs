package com.xrc.mcs.controller;

import com.xrc.mcs.dto.MaterialInfoDto;
import com.xrc.mcs.dto.PairMaterialInfoDto;
import com.xrc.mcs.services.MaterialsManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "materials_manager")
@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
@Slf4j
public class MaterialManagerController {
    private final MaterialsManager materialsManager;

    @Operation(summary = "Add new material")
    @PostMapping("/add")
    public ResponseEntity<String> newMaterial(@RequestBody PairMaterialInfoDto dto) {
        materialsManager.addMaterialBasedOnMaterial(dto.getTargetMaterial(), dto.getSourceMaterial());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("material " + dto.getTargetMaterial().getMaterialName() + " has been added successfully");
    }

    @Operation(summary = "Delete a material")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMaterial(@RequestParam(name = "materialName") String materialName,
                                                 @RequestParam(name = "materialDensity") double density) {
        materialsManager.deleteMaterial(materialName,(float) density);
        return ResponseEntity.status(HttpStatus.OK)
                .body("material " + materialName + " " + density + " has been deleted successfully");
    }

    @Operation(summary = "Update material name/density")
    @PutMapping("/update")
    public void updateMaterialNameDensity(@RequestBody MaterialInfoDto mat,
                                          @RequestParam(name = "materialName") String materialName,
                                          @RequestParam(name = "materialDensity") double density) {
        materialsManager.updateMaterial(mat, materialName, (float) density);
    }
}
