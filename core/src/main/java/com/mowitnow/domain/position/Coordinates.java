package com.mowitnow.domain.position;

import java.io.Serializable;

public record Coordinates(int x, int y) implements Serializable {

    public boolean isWithin(Coordinates boundaries) {
        return this.x() >= 0 && this.x() <= boundaries.x()
                && this.y() >= 0 && this.y() <= boundaries.y();
    }
}
