package com.xrc.mcs.repository;

import com.xrc.mcs.entity.Dmd;
import com.xrc.mcs.entity.DmdParamStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DmdParamStorageRepository extends JpaRepository<DmdParamStorage, Long> {
    @Query("from DmdParamStorage as storage where storage.roomCategory.name = :name ")
    DmdParamStorage findByName(@Param(value = "name") String name);

    @Query("from Dmd as dmd left join DmdParamStorage as storage on dmd.id = storage.dmd.id where storage.roomCategory.name = :name")
    Dmd findDmdByCategoryName(String name);
}
