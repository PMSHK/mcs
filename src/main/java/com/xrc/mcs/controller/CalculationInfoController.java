package com.xrc.mcs.controller;

import com.xrc.mcs.enums.Direction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calculation_info")
@RequiredArgsConstructor
@Slf4j
public class CalculationInfoController {
    private final Direction directions;

//    @GetMapping("/materials")
//    public List<MaterialDto> getAllMatSamples() {
//
//    }
//
//    @GetMapping("/person_types")
//    public List<String> getAllPersonTypes() {
//
//    }

    @GetMapping("/direction_coefficient")
    public List<Double> getDirectionCoefficient() {
        return directions.getValues();
    }
}
