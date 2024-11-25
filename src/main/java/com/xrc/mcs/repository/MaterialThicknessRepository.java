package com.xrc.mcs.repository;

import com.xrc.mcs.entity.MaterialThickness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialThicknessRepository extends JpaRepository<MaterialThickness, Integer> {

}
