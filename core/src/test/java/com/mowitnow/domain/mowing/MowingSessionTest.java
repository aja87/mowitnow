package com.mowitnow.domain.mowing;

import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MowingSessionTest {

    @Test
    void shouldDeployMowerWhenNoCoordinatesConflict() {
        MowingSession session = new MowingSession(new Lawn(new Coordinates(5,5)));
        MowerSetup setup = new MowerSetup(new Position(new Coordinates( 1, 1), Orientation.NORTH) , new ArrayList<>());

        boolean result = session.deploy(setup);

        assertTrue(result);
        assertFalse(session.isPositionEmpty(setup.initialPosition().coordinates()));
    }

    @Test
    void shouldNotDeployMowerWhenPositionIsOutOfBound() {
        MowingSession session = new MowingSession(new Lawn(new Coordinates(5,5)));
        MowerSetup setup = new MowerSetup(new Position(new Coordinates( 6, 6), Orientation.NORTH) , new ArrayList<>());

        boolean result = session.deploy(setup);

        assertFalse(result);
    }

    @Test
    void shouldNotDeployMowerWhenPositionIsOccupied() {
        MowingSession session = new MowingSession(new Lawn(new Coordinates(5,5)));
        MowerSetup setup = new MowerSetup(new Position(new Coordinates( 1, 1), Orientation.NORTH) , new ArrayList<>());

        session.deploy(setup);
        boolean result = session.deploy(setup);

        assertFalse(result);
    }
}