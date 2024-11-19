package com.xrc.mcs.services;

import com.xrc.mcs.dto.DmdDto;
import com.xrc.mcs.entity.Dmd;
import com.xrc.mcs.mapper.DmdMapper;
import com.xrc.mcs.repository.DmdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DmdService {
    private final DmdRepository dmdRepository;
    private final DmdStorageService dmdStorageService;
    private final DmdMapper dmdMapper;
    public void save(DmdDto dto) {
        log.info("save dto: {}", dto);
        dmdRepository.save(dmdMapper.toEntity(dto));
    }
    public List<Dmd> findAll() {
        log.info("Find all dmd values");
        return dmdRepository.findAll();
    }
    public double getDmdByRoomCategory(String roomCategory){
        return dmdStorageService.getDmdByCategoryName(roomCategory).getValue();
    }
}
