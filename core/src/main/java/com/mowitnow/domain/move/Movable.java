package com.mowitnow.domain.move;

import com.mowitnow.domain.position.Position;

public interface Movable {

    void setPosition(Position position);
    Position getPosition();
}
