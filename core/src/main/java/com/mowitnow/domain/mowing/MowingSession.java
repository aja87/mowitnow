package com.mowitnow.domain.mowing;

import com.mowitnow.domain.move.Instruction;
import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Position;
import com.mowitnow.domain.move.MoveFactory;
import com.mowitnow.domain.move.MovingContext;

import java.util.*;
import java.util.stream.Collectors;


public class MowingSession implements MovingContext {
    private final Lawn lawn;
    private final Set<Coordinates> occupiedCoordinates = new HashSet<>();

    private final List<Mower> mowers = new ArrayList<>();
    private final MowerController mowerController;

    public MowingSession(Lawn lawn) {
        this.lawn = lawn;
        mowerController = new MowerController(new MoveFactory());

    }

    public void start() {
        for (Mower mower : this.mowers) {
            for (Instruction instruction : mower.commands) {
                Coordinates initial = mower.getPosition().coordinates();
                mowerController.run(this, mower, instruction);
                occupiedCoordinates.remove(initial);
                occupiedCoordinates.add(mower.getPosition().coordinates());
            }
        }
    }

    public boolean deploy(MowerSetup setup) {
        if (!canMoveToPosition(setup.initialPosition()))
            return false;

        Mower mower = new Mower(setup);
        this.mowers.add(mower);
        this.occupiedCoordinates.add(mower.getPosition().coordinates());
        return true;
    }

    public List<Position> getPositions() {
        return this.mowers
                .stream()
                .map(x -> x.getPosition())
                .collect(Collectors.toList());
    }

    public boolean isPositionEmpty(Coordinates coordinates) {
        return !occupiedCoordinates.contains(coordinates);
    }

    @Override
    public boolean canMoveToPosition(Position position) {
        if(!position.coordinates().isWithin(lawn.boundaries()) && this.isPositionEmpty(position.coordinates())) {
            return  false;
        }
        return position.coordinates().isWithin(lawn.boundaries()) && this.isPositionEmpty(position.coordinates());
    }
}
