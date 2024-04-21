package com.mowitnow.domain.move;


import lombok.RequiredArgsConstructor;


import java.util.Map;

@RequiredArgsConstructor
public class MoveFactory {
    private final Map<Instruction, Move> movesByType = Map.ofEntries(
            Map.entry(Instruction.TURN_RIGHT, new RightTurnMove()),
            Map.entry(Instruction.TURN_LEFT, new LeftTurnMove()),
            Map.entry(Instruction.ADVANCE, new AdvanceMove())
            );
    public Move get(Instruction moveType) {
        return this.movesByType.get(moveType);
    }
}
