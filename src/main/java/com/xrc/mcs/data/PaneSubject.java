package com.xrc.mcs.data;

import lombok.Data;

@Data
public class PaneSubject {
    private final int id;
    private GeneralData generalData;

    public PaneSubject(int id) {
        this.id = id;
        generalData = new GeneralData();
    }
}
