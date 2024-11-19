package com.xrc.mcs.repository;

import com.xrc.mcs.entity.Dmd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DmdRepository extends JpaRepository<Dmd, Long> {

}
