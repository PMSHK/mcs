package com.xrc.mcs.services;

import com.xrc.mcs.entity.RoomCategory;
import com.xrc.mcs.repository.DmdRepository;
import com.xrc.mcs.repository.RoomCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomCategoryService {
    private final RoomCategoryRepository roomCategoryRepository;
    private final DmdService dmdService;

    public List<String> findAll() {
        return roomCategoryRepository.findAll().stream().map(RoomCategory::getName).toList();
    }
    public double findDmdByRoomCategoryName(String roomCategoryName) {
        log.info("looking for a dmd value for: {}", roomCategoryName);
        return dmdService.getDmdByRoomCategory(roomCategoryName);
    }
}
