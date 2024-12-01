package com.xrc.mcs.services;

import com.xrc.mcs.data.Opening;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OpeningsService {
    private final Map<String, Opening> opening;

    @Autowired
    public OpeningsService(List<Opening> opening) {
        this.opening = opening.stream().collect(Collectors.toMap(op -> op.getClass().getSimpleName().toLowerCase(), op -> op));
    }

    public String getLeadEquivalent(String name, double leadEquivalent, double precision) {
        return opening.get(name).getLeadProtectionLevel(leadEquivalent, precision);
    }
}
