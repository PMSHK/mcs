package com.xrc.mcs.controller;

import com.xrc.mcs.dto.DmdParamDto;
import com.xrc.mcs.dto.KParamDto;
import com.xrc.mcs.dto.KermaParamDto;
import com.xrc.mcs.dto.ProtectionDto;
import com.xrc.mcs.services.CalculationService;
import com.xrc.mcs.services.ProtectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "protection_calculator")
@RestController
@RequestMapping("/calculation")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CalculationController {
    private final CalculationService calculationService;
    private final ProtectionService protectionService;

    @Operation(summary = "Get attenuation coefficient and lead equivalent")
    @PostMapping("/protection")
    public ProtectionDto getProtection(@RequestBody KParamDto dto) {

        return protectionService.getProtectionKAndLeadEquivalent(dto);
    }

    @Operation(summary = "Get K")
    @PostMapping("/k")
    public Double getK(@RequestBody KParamDto dto) {

        return calculationService.calculate(dto);
    }

    @Operation(summary = "Get DMD by specific parameters")
    @PostMapping("/dmd")
    public Double getDmd(@RequestBody DmdParamDto dto) {
        return calculationService.calculate(dto);
    }

    @Operation(summary = "Get kerma by voltage ")
    @PostMapping("/kerma")
    public Double getKerma(@RequestBody KermaParamDto dto) {
        return calculationService.calculate(dto);
    }
}
