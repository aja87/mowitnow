package com.mowitnow.domain.move;

import lombok.Getter;

import java.util.Arrays;

public enum Instruction {

    ADVANCE('A'),
    TURN_RIGHT('D'),
    TURN_LEFT('G');

    @Getter
    private final char code;

    Instruction(char value) {
        this.code = value;
    }

    public static Instruction getByCode(char code) {
        return Arrays.stream(Instruction.values()).
                filter(x -> x.code == code)
                .findFirst()
                .orElse(null);
    }
}
