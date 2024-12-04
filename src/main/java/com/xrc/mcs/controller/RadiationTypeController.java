package com.xrc.mcs.controller;

import com.xrc.mcs.dto.RadTypeDto;
import com.xrc.mcs.dto.RadiationTypeDto;
import com.xrc.mcs.enums.RadiationTypes;
import com.xrc.mcs.services.RadiationTypeService;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/radiation_type")
@Validated
public class RadiationTypeController {
    private final RadiationTypeService radiationTypeService;

    @PostMapping("/types")
    public List<String> getRadiationTypes(@RequestBody(required = false) RadTypeDto radTypeDto) {
        log.info("getRadiationTypes");
        return radiationTypeService.getAllRadiationTypes(radTypeDto.getType());
    }

    @GetMapping("/{name}")
    public RadiationTypeDto getRadiationType(@PathVariable String name, @RequestParam(name = "type",required = false, defaultValue = "MED") String type) {
        log.info("getRadiationType");
        return radiationTypeService.getRadiationTypeInfo(name, RadiationTypes.valueOf(type));
    }
}
