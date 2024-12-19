package com.xrc.mcs.services;

import com.xrc.mcs.entity.RoomCategory;
import com.xrc.mcs.repository.ProtectionCacheRepository;
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
    private final ProtectionCacheRepository pcRepository;

    public List<String> findAll() {
        List<String> list = pcRepository.getFromCache("roomCategories", List.class);
        if (list == null) {

            list = roomCategoryRepository.findAll().stream().map(RoomCategory::getName).toList();
            log.info("findAll roomCategories in DB and put into cache");
            pcRepository.saveToCache("roomCategories", list);
        }
        log.info("find all room categories if DB");
        return list;
    }

    public double findDmdByRoomCategoryName(String roomCategoryName) {
        Double result = pcRepository.getFromCache("dmdFor:" + roomCategoryName, Double.class);
        if (result == null) {
            log.info("looking for a dmd value for: {} in DB", roomCategoryName);
            result = dmdService.getDmdByRoomCategory(roomCategoryName);
            log.info("dmd for category \"{}\" was saved to cache", roomCategoryName);
            pcRepository.saveToCache("dmdFor:" + roomCategoryName, result);
        }

        return result;
    }
}
