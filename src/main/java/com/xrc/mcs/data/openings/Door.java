package com.xrc.mcs.data.openings;

import com.xrc.mcs.data.Opening;
import lombok.Setter;

@Setter
public class Door extends Opening {
    private String leadProtectionLevel;

    public Door(String name) {
        super(name);
    }

    @Override
    public String getLeadProtectionLevel() {
        return leadProtectionLevel;
    }

}
