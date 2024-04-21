package com.mowitnow.application.reader;

import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MowerPositionLineConvertorTest {

    MowerPositionLineConvertor cut;

    @BeforeEach
    void setUp() {
        cut = new MowerPositionLineConvertor();
        assertNotNull(cut);
    }

    private static Stream<Arguments> validationTestData() {
        return Stream.of(
                Arguments.of("1 5 N", true),
                Arguments.of("3 6 S", true),
                Arguments.of("0 9 W", true),
                Arguments.of("1 1 E", true),
                Arguments.of("11 99", false),
                Arguments.of("", false),
                Arguments.of(" ", false),
                Arguments.of("A A E", false),
                Arguments.of("11 1 N", false),
                Arguments.of("01N", false),
                Arguments.of("5 5 A", false)
        );
    }


    private static Stream<Arguments> convertTestData() {
        return Stream.of(
                Arguments.of("1 5 N", new Position(new Coordinates(1, 5), Orientation.NORTH)),
                Arguments.of("5 5 S", new Position(new Coordinates(5, 5), Orientation.SOUTH)),
                Arguments.of("6 5 W", new Position(new Coordinates(6, 5), Orientation.WEST)),
                Arguments.of("4 3 E", new Position(new Coordinates(4, 3), Orientation.EAST))
        );
    }

    @ParameterizedTest
    @MethodSource("validationTestData")
    void isValid(String input, boolean expected) {
        boolean result = cut.isValid(input);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("convertTestData")
    void convert(String input, Position expected) {
        Position result = cut.convert(input);
        assertEquals(expected, result);
    }



}