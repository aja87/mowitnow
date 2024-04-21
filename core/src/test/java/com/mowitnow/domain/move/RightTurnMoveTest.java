package com.mowitnow.domain.move;

import com.mowitnow.domain.mowing.Mower;
import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RightTurnMoveTest {

    RightTurnMove cut;

    @BeforeEach
    void setUp() {
        cut = new RightTurnMove();
        assertNotNull(cut);
    }

    private static Stream<Arguments> turnRightTestData() {
        return Stream.of(
                Arguments.of(Orientation.NORTH, Orientation.EAST),
                Arguments.of(Orientation.WEST, Orientation.NORTH),
                Arguments.of(Orientation.SOUTH, Orientation.WEST),
                Arguments.of(Orientation.EAST, Orientation.SOUTH)
        );
    }

    @ParameterizedTest
    @MethodSource("turnRightTestData")
    void shouldApplyTheRightOrientation(Orientation input, Orientation expected) {
        MovingContext context = mock(MovingContext.class);
        Coordinates coordinates = new Coordinates(new Random().nextInt(), new Random().nextInt());
        Position position = new Position(coordinates, input);
        Movable mower = new Mower(position, new ArrayList<>());

        cut.execute(context, mower);

        assertEquals(expected, mower.getPosition().orientation());
    }

    @ParameterizedTest
    @MethodSource("turnRightTestData")
    void shouldKeepTheSameCoordinates(Orientation input) {
        MovingContext context = mock(MovingContext.class);
        Coordinates coordinates = new Coordinates(new Random().nextInt(), new Random().nextInt());
        Position position = new Position(coordinates, input);
        Movable mower = new Mower(position, new ArrayList<>());

        cut.execute(context, mower);

        assertEquals(coordinates, mower.getPosition().coordinates());
    }
}