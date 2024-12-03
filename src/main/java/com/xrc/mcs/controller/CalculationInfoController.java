package com.xrc.mcs.controller;

import com.xrc.mcs.dto.LeadEquivalentParamDto;
import com.xrc.mcs.enums.Direction;
import com.xrc.mcs.services.LeadEquivalentService;
import com.xrc.mcs.services.RoomCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calculation_info")
@RequiredArgsConstructor
@Slf4j
@Valid
public class CalculationInfoController {
    private final Direction directions;
    private final RoomCategoryService roomCategoryService;
    private final LeadEquivalentService leadEquivalentService;

    @GetMapping("/direction_coefficient")
    public List<Double> getDirectionCoefficient() {
        return directions.getValues();
    }

    @GetMapping("/room_categories")
    public List<String> getRoomCategories() {
        return roomCategoryService.findAll();
    }

    @GetMapping("/dmd")
    public Double getDmdByRoomCategory(@RequestParam(name = "room_category") String roomCategory) {
        return roomCategoryService.findDmdByRoomCategoryName(roomCategory);
    }

    @PostMapping("/lead_equivalent_thickness")
    public double getLeadEquivalentThickness(@RequestBody @Valid LeadEquivalentParamDto dto) {
        return leadEquivalentService.getLeadEquivalentThickness(dto.getVoltage(), dto.getAttenuationFrequency());
    }
}
