package com.xrc.mcs.controller;

import com.xrc.mcs.dto.RadTypeDto;
import com.xrc.mcs.dto.RadiationTypeDto;
import com.xrc.mcs.enums.RadiationTypes;
import com.xrc.mcs.services.RadiationTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "rad_type")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/radiation_type")
@Validated
public class RadiationTypeController {
    private final RadiationTypeService radiationTypeService;

    @Operation(summary = "Get all radiation types")
    @PostMapping("/types")
    public List<String> getRadiationTypes(@RequestBody(required = false) RadTypeDto radTypeDto) {
        log.info("getRadiationTypes");
        return radiationTypeService.getAllRadiationTypes(radTypeDto.getType());
    }
    @Operation(summary = "Get radiation type data by name")
    @GetMapping("/{name}")
    public RadiationTypeDto getRadiationType(@Parameter(description = "Radiation Type name") @PathVariable String name, @RequestParam(name = "type",required = false, defaultValue = "MED") String type) {
        log.info("getRadiationType");
        return radiationTypeService.getRadiationTypeInfo(name, RadiationTypes.valueOf(type));
    }
}
