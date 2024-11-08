package com.xrc.mcs.data;

import lombok.Getter;

@Getter
public abstract class Opening {
    private final String name;

    public Opening(String name) {
        this.name = name;
    }

    public abstract String getLeadProtectionLevel();
}
