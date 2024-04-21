package com.mowitnow.domain.mowing;

import com.mowitnow.domain.move.Movable;
import com.mowitnow.domain.move.MoveFactory;
import com.mowitnow.domain.move.MovingContext;
import lombok.RequiredArgsConstructor;
import com.mowitnow.domain.move.Instruction;

@RequiredArgsConstructor
public class MowerController {

    private final MoveFactory moveFactory;

    public void run(MovingContext context, Movable movable, Instruction instruction) {
            moveFactory.get(instruction).execute(context, movable);
    }
}
