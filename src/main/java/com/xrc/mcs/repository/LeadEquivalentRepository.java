package com.xrc.mcs.repository;

import com.xrc.mcs.entity.LeadEquivalent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadEquivalentRepository extends JpaRepository<LeadEquivalent, Long> {
}
