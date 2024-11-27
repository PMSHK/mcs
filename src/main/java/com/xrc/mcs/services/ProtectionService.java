package com.xrc.mcs.services;

import com.xrc.mcs.mapper.MaterialMapper;
import com.xrc.mcs.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProtectionService {
    private final ProtectionUtilService protectionUtilService;

    public List<String> getAllMaterials() {
        return protectionUtilService.getAllMaterials();
    }
}
