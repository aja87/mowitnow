package com.mowitnow.domain.mowing;


import com.mowitnow.domain.move.Instruction;
import com.mowitnow.domain.position.Position;

import java.io.Serializable;
import java.util.List;


public record MowerSetup(Position initialPosition, List<Instruction> commands) implements Serializable {
}
