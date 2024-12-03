package com.xrc.mcs.services;

import com.xrc.mcs.entity.RoomCategory;
import com.xrc.mcs.repository.RoomCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomCategoryService {
    private final RoomCategoryRepository roomCategoryRepository;
    private final DmdService dmdService;

    @Cacheable(value = "roomCategories")
    public List<String> findAll() {
        log.info("find all room categories if DB");
        return roomCategoryRepository.findAll().stream().map(RoomCategory::getName).toList();
    }
    @Cacheable(value = "dmdByRoomCategory", key = "#roomCategoryName")
    public double findDmdByRoomCategoryName(String roomCategoryName) {
        log.info("looking for a dmd value for: {} in DB", roomCategoryName);
        return dmdService.getDmdByRoomCategory(roomCategoryName);
    }
}
