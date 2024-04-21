package com.mowitnow.domain.mowing;

import com.mowitnow.domain.move.Instruction;
import com.mowitnow.domain.position.Position;
import com.mowitnow.domain.move.Movable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Mower implements Movable {

    Position position;

    List<Instruction> commands;


    public Mower (MowerSetup setup) {
        this.position = setup.initialPosition();
        this.commands = new ArrayList<>(setup.commands());
    }
}
