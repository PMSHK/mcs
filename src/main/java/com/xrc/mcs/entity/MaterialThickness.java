package com.xrc.mcs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "materials_lead_equivalent_storage")
public class MaterialThickness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "thickness")
    private float thickness;
    @ManyToOne()
    @JoinColumn(nullable = false, name = "voltage_id")
    private Voltage voltage;
    @ManyToOne()
    @JoinColumn(nullable = false, name = "material_id")
    private Material material;
    @ManyToOne()
    @JoinColumn(nullable = false, name = "material_lead_equivalent_id")
    private MaterialLeadEquivalent materialLeadEquivalent;

}
