package com.xrc.mcs.repository;

import com.xrc.mcs.entity.RoomCategoryDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCategoryDescRepository extends JpaRepository<RoomCategoryDesc, Long> {
}
