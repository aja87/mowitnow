package com.mowitnow.domain.move;

import com.mowitnow.domain.mowing.Mower;
import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AdvanceMoveTest {

    AdvanceMove  cut = new AdvanceMove();

    private

    @BeforeEach
    void setUp() {
        cut = new AdvanceMove();
        assertNotNull(cut);
    }

    private static Stream<Arguments> advanceTestData() {
        return Stream.of(
                Arguments.of(Orientation.NORTH, new Coordinates(0, 1)),
                Arguments.of(Orientation.WEST, new Coordinates(-1, 0)),
                Arguments.of(Orientation.SOUTH, new Coordinates(0, -1)),
                Arguments.of(Orientation.EAST, new Coordinates(1, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("advanceTestData")
    void shouldSetTheRightCoordinatesWhenMoveIsAllowedInContext(Orientation initialOrientation, Coordinates expectedStep) {
        MovingContext context = mock(MovingContext.class);
        when(context.canMoveToPosition(any())).thenReturn(true);
        Coordinates coordinates = new Coordinates(new Random().nextInt(), new Random().nextInt());
        Position position = new Position(coordinates, initialOrientation);
        Movable movable = new Mower(position, new ArrayList<>());

        cut.execute(context, movable);

        Assertions.assertEquals(coordinates.y() +  expectedStep.y(), movable.getPosition().coordinates().y());
        Assertions.assertEquals(coordinates.x() +  expectedStep.x(), movable.getPosition().coordinates().x());
    }

    @ParameterizedTest
    @MethodSource("advanceTestData")
    void shouldKeepTheSameCoordinatesWhenMoveIsNotAllowed(Orientation initialOrientation, Coordinates expectedStep) {
        MovingContext context = mock(MovingContext.class);
        when(context.canMoveToPosition(any())).thenReturn(false);
        Coordinates coordinates = new Coordinates(new Random().nextInt(), new Random().nextInt());
        Position position = new Position(coordinates, initialOrientation);
        Movable movable = new Mower(position, new ArrayList<>());

        cut.execute(context, movable);

        Assertions.assertEquals(coordinates, movable.getPosition().coordinates());
    }


    @ParameterizedTest
    @MethodSource("advanceTestData")
    void shouldKeepTheSameOrientation(Orientation initialOrientation, Coordinates expectedStep) {
        MovingContext context = mock(MovingContext.class);
        when(context.canMoveToPosition(any())).thenReturn(true);
        Coordinates coordinates = new Coordinates(new Random().nextInt(), new Random().nextInt());
        Position position = new Position(coordinates, initialOrientation);
        Movable movable = new Mower(position, new ArrayList<>());

        cut.execute(context, movable);

        Assertions.assertEquals(initialOrientation, movable.getPosition().orientation());
    }
}