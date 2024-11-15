package com.xrc.mcs.controller;

import com.xrc.mcs.dto.DmdParamDto;
import com.xrc.mcs.dto.KParamDto;
import com.xrc.mcs.dto.KermaParamDto;
import com.xrc.mcs.dto.ProtectionDto;
import com.xrc.mcs.services.CalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculation")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CalculationController {
    private final CalculationService calculationService;

    @PostMapping("/protection")
    public ProtectionDto getProtection(@RequestBody KParamDto dto) {

        return calculationService.calculate(dto);
    }

    @PostMapping("/k")
    public Double getK(@RequestBody KParamDto dto) {

        return calculationService.calculate(dto);
    }

    @PostMapping("/dmd")
    public Double getDmd(@RequestBody DmdParamDto dto) {
        return calculationService.calculate(dto);
    }

    @PostMapping("/kerma")
    public Double getKerma(@RequestBody KermaParamDto dto) {
        return calculationService.calculate(dto);
    }
}
