package com.mowitnow.domain.position;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesTest {

    private static Stream<Arguments> isWithinTestData() {
        return Stream.of(
                Arguments.of(new Coordinates(0, 1), new Coordinates(5, 5), true),
                Arguments.of(new Coordinates(1, 3), new Coordinates(7, 7), true),
                Arguments.of(new Coordinates(5, 5), new Coordinates(5, 5), true),
                Arguments.of(new Coordinates(5, 5), new Coordinates(4, 5), false),
                Arguments.of(new Coordinates(5, 5), new Coordinates(5, 4), false),
                Arguments.of(new Coordinates(-1, 1), new Coordinates(5, 5), false),
                Arguments.of(new Coordinates(1, -1), new Coordinates(5, 5), false)
        );
    }

    @ParameterizedTest
    @MethodSource("isWithinTestData")
    void isWithin(Coordinates coordinates, Coordinates boundaries, boolean expected) {
        Boolean result = coordinates.isWithin(boundaries);

        assertEquals(expected, result);
    }
}