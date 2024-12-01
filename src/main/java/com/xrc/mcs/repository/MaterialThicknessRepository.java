package com.xrc.mcs.repository;

import com.xrc.mcs.entity.MaterialThickness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialThicknessRepository extends JpaRepository<MaterialThickness, Integer> {
    @Query(value = """
            with low_voltage as (select v.value from voltage as v where v.value<= :voltage order by v.value desc limit 1),
            high_voltage as (select v.value from voltage as v where v.value> :voltage limit 1)
            select mat.density, m.thickness, le.value, v.value from materials_lead_equivalent_storage as m
                                         join material mat on mat.id = m.material_id
                                         join voltage v on v.id = m.voltage_id
                                         join material_lead_equivalent le on le.id = m.material_lead_equivalent_id
            where mat.name=:name
            and v.value between (select * from low_voltage) and (select * from high_voltage)
            """, nativeQuery = true)
    List<Object[]> getAllMatThicknessesOnVoltage(@Param(value = "name") String name,
                                                 @Param(value = "voltage") double voltage);

    @Query(value = """
with low_voltage as (select v.value from voltage as v where v.value<= :voltage order by v.value desc limit 1),
     high_voltage as (select v.value from voltage as v where v.value> :voltage limit 1),
     low_le as (select le.value from materials_lead_equivalent_storage as s
                                    join material_lead_Equivalent as le on s.material_lead_equivalent_id=le.id
                                    join material mat on mat.id = s.material_id
                                    where mat.name = :name and le.value <= :lead order by le.value desc limit 1),
     high_le as (select le.value from materials_lead_equivalent_storage as th
                                          join material_lead_Equivalent as le on th.material_lead_equivalent_id=le.id
                                          join material mat on mat.id = th.material_id
                 where mat.name = :name and le.value > :lead limit 1)

select mat.density, m.thickness, le.value, v.value from materials_lead_equivalent_storage as m
                                                            join material mat on mat.id = m.material_id
                                                            join voltage v on v.id = m.voltage_id
                                                            join material_lead_equivalent le on le.id = m.material_lead_equivalent_id
where mat.name=:name
  and v.value between (select * from low_voltage) and (select * from high_voltage)
  and le.value between (select * from low_le) and (select * from high_le)
""", nativeQuery = true)
    List<Object[]> getAllMatThicknessesOnVoltage(@Param(value = "name") String name,
                                                 @Param(value = "voltage") double voltage,
                                                 @Param(value = "lead") double lead);
}

