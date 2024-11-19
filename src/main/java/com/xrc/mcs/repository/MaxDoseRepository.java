package com.xrc.mcs.repository;

import com.xrc.mcs.entity.MaxDose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxDoseRepository extends JpaRepository<MaxDose, Long> {
}
