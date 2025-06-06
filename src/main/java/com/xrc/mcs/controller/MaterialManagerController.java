package com.xrc.mcs.controller;

import com.xrc.mcs.dto.MaterialInfoDto;
import com.xrc.mcs.dto.PairMaterialInfoDto;
import com.xrc.mcs.services.MaterialsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
@Slf4j
public class MaterialManagerController {
    private final MaterialsManager materialsManager;

    @PostMapping("/add")
    public ResponseEntity<String> newMaterial(@RequestBody PairMaterialInfoDto dto) {
        materialsManager.addMaterialBasedOnMaterial(dto.getTargetMaterial(), dto.getSourceMaterial());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("material " + dto.getTargetMaterial().getMaterialName() + " has been added successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMaterial(@RequestBody MaterialInfoDto mat) {
        materialsManager.deleteMaterial(mat);
        return ResponseEntity.status(HttpStatus.OK)
                .body("material " + mat.getMaterialName() + " has been deleted successfully");
    }
}
