package com.xrc.mcs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialInfoDto implements Serializable {
    private String name;
    private Float density;

    public String getMaterialName() {
        return
                new StringBuilder(name)
                        .append(" ")
                        .append(density)
                        .toString();
    }
}
