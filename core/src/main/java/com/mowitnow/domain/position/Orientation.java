package com.mowitnow.domain.position;


import lombok.Getter;

import java.util.Arrays;

public enum Orientation {
    NORTH('N'),
    EAST('E'),
    WEST('W'),
    SOUTH('S');

    @Getter
    private final char code;

    Orientation(char value) {
        this.code = value;
    }

    public static Orientation getByCode(char code) {
        return Arrays.stream(Orientation.values()).
                filter(x -> x.code == code)
                .findFirst()
                .orElse(null);
    }
}