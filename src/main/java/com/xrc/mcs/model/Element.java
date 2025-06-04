package com.xrc.mcs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Element {
    private String name;
    private String appearance;
    private double atomic_mass;
    private Double boil;
    private String category;
    private double density;
    private String discovered_by;
    private Double melt;
    private Double molar_heat;
    private String named_by;
    private int number;
    private int period;
    private int group;
    private String phase;
    private String source;
    private String bohr_model_image;
    private String bohr_model_3d;
    private String spectral_img;
    private String summary;
    private String symbol;
    private int xpos;
    private int ypos;
    private int wxpos;
    private int wypos;
    private List<Integer> shells;
    private String electron_configuration;
    private String electron_configuration_semantic;
    private double electron_affinity;
    private Double electronegativity_pauling;
    private List<Double> ionization_energies;
    @JsonProperty("cpk-hex")
    private String cpkHex;
    private Image image;
    private String block;
}
