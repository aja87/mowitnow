package com.mowitnow.domain.move;


import lombok.RequiredArgsConstructor;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;

import java.util.Map;

@RequiredArgsConstructor
public class LeftTurnMove implements Move {
    private  final Map<Orientation, Orientation> leftByOrientation = Map.ofEntries(
            Map.entry(Orientation.EAST, Orientation.NORTH),
            Map.entry(Orientation.WEST, Orientation.SOUTH),
            Map.entry(Orientation.NORTH, Orientation.WEST),
            Map.entry(Orientation.SOUTH, Orientation.EAST)
    );


    @Override
    public void execute(MovingContext lawn, Movable movable) {
        movable.setPosition(getNextPosition(movable));
    }

    private Position getNextPosition(Movable movable) {
        return new Position(
                movable.getPosition().coordinates(),
                leftByOrientation.get(movable.getPosition().orientation()));
    }
}
