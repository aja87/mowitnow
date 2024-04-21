package com.mowitnow.application.writer;

import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OutputLineConverterTest {

    private OutputLineConverter cut;
    @BeforeEach
    void setUp() {
        cut = new OutputLineConverter();
        assertNotNull(cut);
    }

    private static Stream<Arguments> convertTestData() {
        return Stream.of(
                Arguments.of( new Position(new Coordinates(1, 5), Orientation.NORTH), "1 5 N"),
                Arguments.of( new Position(new Coordinates(5, 5), Orientation.SOUTH), "5 5 S"),
                Arguments.of( new Position(new Coordinates(6, 5), Orientation.WEST), "6 5 W"),
                Arguments.of( new Position(new Coordinates(4, 3), Orientation.EAST), "4 3 E")
        );
    }

    @ParameterizedTest
    @MethodSource("convertTestData")
    void convert(Position input, String expected) {
        String result = cut.convert(input);
        assertEquals(expected, result);
    }
}