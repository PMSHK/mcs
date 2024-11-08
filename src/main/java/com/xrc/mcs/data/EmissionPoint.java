package com.xrc.mcs.data;

import com.xrc.mcs.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmissionPoint {

    private Direction direction;
    private long distance;
}
