package com.xrc.mcs.controller;

import com.xrc.mcs.model.Element;
import com.xrc.mcs.services.PeriodicTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "periodic_table")
@RestController
@RequiredArgsConstructor
@RequestMapping("/periodic_table")
public class PeriodicTableController {
    private final PeriodicTableService periodicTableService;

    @Operation(summary = "Get all elements from periodic table")
    @GetMapping("/all")
    public List<Element> getAllElements() {
        return periodicTableService.getAllElements();
    }

    @Operation(summary = "Get element from periodic table")
    @GetMapping("/{var}")
    public Element getMaterial(@PathVariable String var, @RequestParam(value = "type") String type) {
        return periodicTableService.getElement(type,var);
    }
}
