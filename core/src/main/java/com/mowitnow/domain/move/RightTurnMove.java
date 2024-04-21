package com.mowitnow.domain.move;
import lombok.RequiredArgsConstructor;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;

import java.util.Map;

@RequiredArgsConstructor
public class RightTurnMove implements Move {

    private  final Map<Orientation, Orientation> rightByOrientation = Map.ofEntries(
            Map.entry(Orientation.EAST, Orientation.SOUTH),
            Map.entry(Orientation.WEST, Orientation.NORTH),
            Map.entry(Orientation.NORTH, Orientation.EAST),
            Map.entry(Orientation.SOUTH, Orientation.WEST)
    );

    @Override
    public void execute(MovingContext context, Movable movable) {
        movable.setPosition(getNextPosition(movable));
    }

    private Position getNextPosition(Movable movable) {
        return new Position(
                movable.getPosition().coordinates(),
                rightByOrientation.get(movable.getPosition().orientation()));
    }
}
