package com.mowitnow.domain.mowing;

import com.mowitnow.domain.move.Instruction;
import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MowingServiceTest {

    private MowingService cut;

    @BeforeEach
    void setUp() {
        cut = new MowingService();
        assertNotNull(cut);
    }

    @Test
    void mow() {

        MowingRequest request = new MowingRequest(new Coordinates(5, 5),
                Arrays.asList(
                        new MowerSetup(new Position(new Coordinates(1, 2), Orientation.NORTH),
                                Arrays.asList(
                                        Instruction.TURN_LEFT,
                                        Instruction.ADVANCE,
                                        Instruction.TURN_LEFT,
                                        Instruction.ADVANCE,
                                        Instruction.TURN_LEFT,
                                        Instruction.ADVANCE,
                                        Instruction.TURN_LEFT,
                                        Instruction.ADVANCE,
                                        Instruction.ADVANCE)
                        ),
                        new MowerSetup(new Position(new Coordinates(3, 3), Orientation.EAST),
                                Arrays.asList(
                                        Instruction.ADVANCE,
                                        Instruction.ADVANCE,
                                        Instruction.TURN_RIGHT,
                                        Instruction.ADVANCE,
                                        Instruction.ADVANCE,
                                        Instruction.TURN_RIGHT,
                                        Instruction.ADVANCE,
                                        Instruction.TURN_RIGHT,
                                        Instruction.TURN_RIGHT,
                                        Instruction.ADVANCE)
                        )
                )
        );


        List<Position> finalPositions = cut.mow(request);

        assertIterableEquals(Arrays.asList(new Position(new Coordinates(1, 3), Orientation.NORTH), new Position(new Coordinates(5, 1), Orientation.EAST)),
                finalPositions);

    }
}