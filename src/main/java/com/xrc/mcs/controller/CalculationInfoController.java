package com.xrc.mcs.controller;

import com.xrc.mcs.dto.LeadEquivalentParamDto;
import com.xrc.mcs.enums.Direction;
import com.xrc.mcs.services.LeadEquivalentService;
import com.xrc.mcs.services.RoomCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="")
@RestController
@RequestMapping("/calculation_info")
@RequiredArgsConstructor
@Slf4j
@Valid
public class CalculationInfoController {
    private final Direction directions;
    private final RoomCategoryService roomCategoryService;
    private final LeadEquivalentService leadEquivalentService;

    @Operation(summary = "Get all direction coefficients ")
    @GetMapping("/direction_coefficient")
    public List<Double> getDirectionCoefficient() {
        return directions.getValues();
    }

    @Operation(summary = "Get all room categories")
    @GetMapping("/room_categories")
    public List<String> getRoomCategories() {
        return roomCategoryService.findAll();
    }

    @Operation(summary = "Find max dose based on room category")
    @GetMapping("/dmd")
    public Double getDmdByRoomCategory(@RequestParam(name = "room_category") String roomCategory) {
        return roomCategoryService.findDmdByRoomCategoryName(roomCategory);
    }

    @Operation(summary = "Get lead equivalent or thickness depends on which is not 0")
    @PostMapping("/lead_equivalent_thickness")
    public double getLeadEquivalentThickness(@RequestBody @Valid LeadEquivalentParamDto dto) {
        return leadEquivalentService.getLeadEquivalentThickness(dto.getVoltage(), dto.getAttenuationFrequency());
    }
}
