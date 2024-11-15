package com.xrc.mcs.entity;

import com.xrc.mcs.enums.RadiationTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "radiation_type")
public class RadiationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 1000, nullable = false)
    private String name;
    @Column(nullable = false)
    private long voltage;
    @Column(nullable = false)
    private long radiationExit;
    @Column(length = 4, nullable = false)
    @Enumerated(EnumType.STRING)
    private RadiationTypes type;
}
