package com.xrc.mcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KermaRepository extends JpaRepository<Kerma, Long> {

    public double getKerma(double voltage);
}
