package com.mowitnow.domain.move;

import com.mowitnow.domain.position.Position;

public interface MovingContext {
    boolean canMoveToPosition(Position position);
}
