package com.mowitnow.application.reader;

import com.mowitnow.domain.move.Instruction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CommandsLineConvertorTest {

    private CommandsLineConvertor cut;

    @BeforeEach
    void setUp() {
        cut = new CommandsLineConvertor();
        assertNotNull(cut);
    }


    private static Stream<Arguments> validationTestData() {
        return Stream.of(
                Arguments.of("AGD", true),
                Arguments.of("DGAAAA", true),
                Arguments.of("AAAAAA", true),
                Arguments.of("GGGGG", true),
                Arguments.of("G", true),
                Arguments.of("", false),
                Arguments.of(" ", false),
                Arguments.of("A A", false),
                Arguments.of("AB", false),
                Arguments.of("5464", false)
        );
    }

    private static Stream<Arguments> convertTestData() {
        return Stream.of(
                Arguments.of("A", List.of(Instruction.ADVANCE)),
                Arguments.of("AGD", Arrays.asList(Instruction.ADVANCE, Instruction.TURN_LEFT, Instruction.TURN_RIGHT)),
                Arguments.of("AAAA", Arrays.asList(Instruction.ADVANCE, Instruction.ADVANCE, Instruction.ADVANCE, Instruction.ADVANCE)),
                Arguments.of("GGAD", Arrays.asList(Instruction.TURN_LEFT, Instruction.TURN_LEFT, Instruction.ADVANCE,  Instruction.TURN_RIGHT))
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
    void convert(String input, List<Instruction> expected) {
        List<Instruction> result = cut.convert(input);
        assertIterableEquals(expected, result);
    }


}