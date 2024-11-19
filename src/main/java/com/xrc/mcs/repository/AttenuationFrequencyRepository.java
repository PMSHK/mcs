package com.xrc.mcs.repository;

import com.xrc.mcs.entity.AttenuationFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttenuationFrequencyRepository extends JpaRepository<AttenuationFrequency, Long> {
}
