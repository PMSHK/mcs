package com.xrc.mcs.entity;

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
@Table(name = "dmd_param_storage")
public class DmdParamStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "room_category_id")
    private RoomCategory roomCategory;
    @ManyToOne
    @JoinColumn(name = "dmd_id")
    private Dmd dmd;
    @ManyToOne
    @JoinColumn(name = "max_dose_id")
    private MaxDose maxDose;
    @ManyToOne
    @JoinColumn(name = "work_shift_id")
    private WorkShift workShift;
    @ManyToOne
    @JoinColumn(name = "room_load_coefficient_id")
    private RoomLoadCoefficient roomLoadCoefficient;

}
