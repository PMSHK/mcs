package com.xrc.mcs.repository;

import com.xrc.mcs.entity.Voltage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoltageRepository extends JpaRepository<Voltage, Long> {
}
