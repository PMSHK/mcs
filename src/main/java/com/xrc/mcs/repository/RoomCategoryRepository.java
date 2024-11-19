package com.xrc.mcs.repository;

import com.xrc.mcs.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
    @Query("select room.description from RoomCategory as room where room.name = :name")
    String getDescription(@Param(value = "name") String categoryName);
}
