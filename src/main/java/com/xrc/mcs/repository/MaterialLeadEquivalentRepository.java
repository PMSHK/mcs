package com.xrc.mcs.repository;

import com.xrc.mcs.entity.MaterialLeadEquivalent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialLeadEquivalentRepository extends JpaRepository<MaterialLeadEquivalent, Long> {

}
