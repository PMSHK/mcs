package com.xrc.mcs.controller;

import com.xrc.mcs.dto.MaterialDto;
import com.xrc.mcs.dto.ResultLeadEquivalentDto;
import com.xrc.mcs.services.ProtectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/protection")
@RequiredArgsConstructor
@Slf4j
@Valid
public class ProtectionController {
    public final ProtectionService protectionService;

    @GetMapping("/all_materials")
    public List<String> allMaterials() {
        return protectionService.getAllMaterials();
    }

    @PostMapping("/material_lead_equivalent")
    public Double getMaterialLeadEquivalent(@Valid @RequestBody() MaterialDto dto) {
        return protectionService.getMaterialLeadEquivalent(dto);
    }

    @PostMapping("/additional_protection")
    public String getAdditionalProtection(@Valid @RequestBody ResultLeadEquivalentDto dto){
        return protectionService.getAdditionalProtection(dto);
    }
}
