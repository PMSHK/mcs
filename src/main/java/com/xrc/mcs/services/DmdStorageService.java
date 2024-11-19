package com.xrc.mcs.services;

import com.xrc.mcs.entity.Dmd;
import com.xrc.mcs.entity.DmdParamStorage;
import com.xrc.mcs.repository.DmdParamStorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DmdStorageService {
    private final DmdParamStorageRepository dmdStorageRepository;
    public List<DmdParamStorage> getDmdStorage() {
        return dmdStorageRepository.findAll();
    }
    public Dmd getDmdByCategoryName(String categoryName){
        return dmdStorageRepository.findDmdByCategoryName(categoryName);
    }
}
