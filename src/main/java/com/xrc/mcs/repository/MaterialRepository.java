package com.xrc.mcs.repository;

import com.xrc.mcs.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Query("SELECT m FROM Material m LEFT JOIN FETCH m.materialThicknessList WHERE m.name = :name and abs(m.density - :density)<0.01 ")
    Optional<Material> findByNameAndDensityWithThicknesses(@Param("name") String name, @Param("density") double density);


}
