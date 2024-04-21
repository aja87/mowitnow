package com.mowitnow.application.reader;

import com.mowitnow.domain.position.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LawnLineConverterTest {

    LawnLineConverter cut;

    @BeforeEach
    void setUp() {
        cut = new LawnLineConverter();
        assertNotNull(cut);
    }

    private static Stream<Arguments> convertTestData() {
        return Stream.of(
                Arguments.of("1 5", new Coordinates(1, 5)),
                Arguments.of("3 6", new Coordinates(3, 6)),
                Arguments.of("5 5", new Coordinates(5, 5)),
                Arguments.of("1 7", new Coordinates(1, 7)),
                Arguments.of("01 99", new Coordinates(1, 99)),
                Arguments.of("19 308", new Coordinates(19, 308))
        );
    }


    private static Stream<Arguments> validationTestData() {
        return Stream.of(
                Arguments.of("1 5", true),
                Arguments.of("3 6", true),
                Arguments.of("0 9", true),
                Arguments.of("01 1", true),
                Arguments.of("11 99", true),
                Arguments.of("", false),
                Arguments.of(" ", false),
                Arguments.of("A A", false),
                Arguments.of("AB", false),
                Arguments.of("011", false)
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
    void convert(String input, Coordinates expected) {
        Coordinates result = cut.convert(input);
        assertEquals(expected, result);
    }
}