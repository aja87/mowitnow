package com.mowitnow.domain.move;
import lombok.RequiredArgsConstructor;
import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;

import java.util.Map;

@RequiredArgsConstructor
public class AdvanceMove implements Move {

    private final Map<Orientation, Coordinates> stepByOrientation = Map.ofEntries(
            Map.entry(Orientation.EAST, new Coordinates(1, 0)),
            Map.entry(Orientation.WEST, new Coordinates(-1, 0)),
            Map.entry(Orientation.NORTH, new Coordinates(0, 1)),
            Map.entry(Orientation.SOUTH, new Coordinates(0, -1))
    );

    @Override
    public void execute(MovingContext context, Movable movable) {
        Position nextPosition = getNextPosition(movable);

        if (this.canMove(context, nextPosition)) {
            movable.setPosition(nextPosition);
        }
    }

    private boolean canMove(MovingContext area, Position nextPosition) {
        return area.canMoveToPosition(nextPosition);
    }



    private Position getNextPosition(Movable movable) {
        Coordinates step = stepByOrientation.get(movable.getPosition().orientation());
        return new Position(
                new Coordinates(
                        movable.getPosition().coordinates().x() + step.x(),
                        movable.getPosition().coordinates().y() + step.y()),
                movable.getPosition().orientation());
    }
}
