package com.mowitnow.domain.move;

public interface Move {

    void execute(MovingContext movingContext, Movable movable);

}
