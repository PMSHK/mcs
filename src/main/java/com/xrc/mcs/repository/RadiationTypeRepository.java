package com.xrc.mcs.repository;

import com.xrc.mcs.entity.RadiationType;
import com.xrc.mcs.enums.RadiationTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RadiationTypeRepository extends JpaRepository<RadiationType, Long> {
    @Query("select rt.name from RadiationType as rt where rt.type = :type")
    List<String> getAllNamesByTypes(@Param(value = "type") RadiationTypes type);

    @Query("from RadiationType as rt where rt.name = :name and rt.type = :type")
    RadiationType getRadiationParamsByTypeAndName(@Param(value = "type") RadiationTypes type, @Param(value = "name") String name);
}

