package com.xrc.mcs.services;

import com.xrc.mcs.model.Element;
import com.xrc.mcs.model.PeriodicTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class PeriodicTableService {
    private final PeriodicTable periodicTable;
    private Map<String, Function<Object, Element>> commands = new HashMap<>();

    @Autowired
    public PeriodicTableService(PeriodicTable periodicTable) {
        this.periodicTable = periodicTable;
        commands.put("name", value -> getElementByName(value.toString()));
        commands.put("symbol", value -> getElementBySymbol(value.toString()));
        commands.put("number", value -> getElementByNumber(Integer.parseInt(value.toString())));
    }

    public Element getElement(String param1, String param2) {
        return commands.get(param1).apply(param2);
    }

    public Element getElementByName(String name) {
        return periodicTable.getElements().stream().filter((x) -> x.getName().equals(name)).findFirst().orElse(null);
    }

    public Element getElementByNumber(int id) {
        return periodicTable.getElements().stream().filter((x) -> x.getNumber() == id).findFirst().orElse(null);
    }

    public List<Element> getAllElements() {
        return periodicTable.getElements();
    }

    public Element getElementBySymbol(String symbol) {
        return periodicTable.getElements().stream().filter((x) -> x.getSymbol().equals(symbol)).findFirst().orElse(null);
    }

}

