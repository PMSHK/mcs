package com.xrc.mcs.controller;

import com.xrc.mcs.data.Computable;
import com.xrc.mcs.dto.KParamDto;
import com.xrc.mcs.dto.ProtectionDto;
import com.xrc.mcs.services.CalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
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
    private ApplicationContext context;

    @PostMapping("/k")
    public ProtectionDto getProtection(@RequestBody KParamDto dto) {
        calculationService.calc(context.getBean("kCalculator", Computable.class), dto);
    }
}
