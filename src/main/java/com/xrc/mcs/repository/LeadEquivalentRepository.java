package com.xrc.mcs.repository;

import com.xrc.mcs.dto.LeadEquivalentParamDto;
import com.xrc.mcs.entity.LeadEquivalent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadEquivalentRepository extends JpaRepository<LeadEquivalent, Long> {

    @Query(nativeQuery = true, value = """
            (
                 with low_attenuation as (select v.value from attenuation_frequency as v where v.value<= :attenuation order by v.value desc limit 1
                 ),
                      high_attenuation as (select v.value from attenuation_frequency as v where v.value> :attenuation limit 1)
             
             
                 SELECT v.value AS voltage, af.value AS attenuation, le.thickness
                 FROM lead_equivalent le
                          JOIN voltage v ON le.voltage_id = v.id
                          JOIN attenuation_frequency af ON le.attenuation_frequency_id = af.id
                 WHERE v.value <= :voltage AND af.value in (select v.value from attenuation_frequency as v where v.value  between (select value from low_attenuation) and (select value from high_attenuation))
                 ORDER BY v.value DESC, af.value DESC
                 LIMIT 2
             )
             UNION ALL
             (
                 with low_attenuation as (select v.value from attenuation_frequency as v where v.value<= :attenuation order by v.value desc limit 1
                 ),
                      high_attenuation as (select v.value from attenuation_frequency as v where v.value> :attenuation limit 1)
                 SELECT v.value AS voltage, af.value AS attenuation, le.thickness
                 FROM lead_equivalent le
                          JOIN voltage v ON le.voltage_id = v.id
                          JOIN attenuation_frequency af ON le.attenuation_frequency_id = af.id
                 WHERE v.value > :voltage AND af.value in (select v.value from attenuation_frequency as v where v.value  between (select value from low_attenuation) and (select value from high_attenuation))
                 ORDER BY v.value ASC, af.value ASC
                 LIMIT 2
             );
            """)
List<Object[]> getLeadParametersNative(@Param(value = "voltage") double voltage, @Param(value = "attenuation") double attenuationFrequency);

    @Query(nativeQuery = true, value = """

                    with low_voltage as (select v.value from voltage as v where v.value<= :voltage order by v.value desc limit 1
                    ),
                         high_voltage as (select v.value from voltage as v where v.value> :voltage limit 1)
                    select v.value from voltage as v where v.value  between (select value from low_voltage) and (select value from high_voltage)
            """)
    public long[] getVoltageRange(@Param(value = "voltage") long voltage);

    @Query(nativeQuery = true, value = """

                    with low_attenuation as (select v.value from attenuation_frequency as v where v.value<= :attenuation order by v.value desc limit 1
                    ),
                         high_attenuation as (select v.value from attenuation_frequency as v where v.value> :attenuation limit 1)
                    select v.value from attenuation_frequency as v where v.value  between (select value from low_attenuation) and (select value from high_attenuation)
            """)
    public long[] getAttenuationRange(@Param(value = "attenuation") long attenuationFrequency);
}
