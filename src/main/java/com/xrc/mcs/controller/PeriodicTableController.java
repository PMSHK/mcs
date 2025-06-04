package com.xrc.mcs.controller;

import com.xrc.mcs.model.Element;
import com.xrc.mcs.services.PeriodicTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/periodic_table")
public class PeriodicTableController {
    private final PeriodicTableService periodicTableService;

    @GetMapping("/all")
    public List<Element> getAllElements() {
        return periodicTableService.getAllElements();
    }

    @GetMapping("/{var}")
    public Element getMaterial(@PathVariable String var, @RequestParam(value = "type") String type) {
        return periodicTableService.getElement(type,var);
    }
//
//    @GetMapping("/{number}")
//    public void getElement(@PathVariable int number) {
//        periodicTableService.getElement(number);
//    }
//
//    @GetMapping("/{number}")
//    public void getElement(@PathVariable int number) {
//        periodicTableService.getElementByNumber(number);
//    }
}
